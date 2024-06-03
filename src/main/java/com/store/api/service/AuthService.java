package com.store.api.service;

import com.store.api.dto.LoginRequestDto;
import com.store.api.dto.LoginResponseDto;
import com.store.api.dto.RegisterRequestDto;
import com.store.api.dto.RegisterResponseDto;
import com.store.api.enums.UserRole;
import com.store.api.exception.ResourceExistsException;
import com.store.api.exception.ResourceNotFoundException;
import com.store.api.model.User;
import com.store.api.repository.UserRepository;
import com.store.api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        boolean exists = userRepository.existsByUsername(registerRequestDto.getUsername());
        if (exists) {
            throw new ResourceExistsException("User with username " + registerRequestDto.getUsername() + " already exists.");
        }

        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setRole(UserRole.USER);
        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());
        user.setAddress(registerRequestDto.getAddress());
        userRepository.save(user);
        String accessToken = jwtService.generateToken(user);
        return RegisterResponseDto.builder().accessToken(accessToken).build();
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );
        Optional<User> user = userRepository.findByUsername(loginRequestDto.getUsername());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User with username " + loginRequestDto.getUsername() + " does not exist.");
        }

        String accessToken = jwtService.generateToken(user.get());
        return LoginResponseDto.builder().accessToken(accessToken).build();
    }
}
