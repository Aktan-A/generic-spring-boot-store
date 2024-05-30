package com.store.api.controller;

import com.store.api.dto.LoginRequestDto;
import com.store.api.dto.LoginResponseDto;
import com.store.api.dto.RegisterDto;
import com.store.api.dto.UserDto;
import com.store.api.enums.UserRole;
import com.store.api.security.JwtIssuer;
import com.store.api.security.UserPrincipal;
import com.store.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private JwtIssuer jwtIssuer;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            UserService userService,
            PasswordEncoder passwordEncoder,
            JwtIssuer jwtIssuer
    ) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtIssuer = jwtIssuer;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterDto registerDto) {
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return ResponseEntity.ok(userService.registerUser(registerDto));
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
//         Authentication authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
//         );
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//
//        UserRole[] role = userPrincipal.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//
//
//        UserDto user = userService.getUserByUsername(loginRequestDto.getUsername());
//        String token = jwtIssuer.issue(userPrincipal.getUserId(), userPrincipal.getUsername(), userPrincipal.getAuthorities()[0]);
//        return ResponseEntity.ok(LoginResponseDto.builder().accessToken(token).build());
//    }
}
