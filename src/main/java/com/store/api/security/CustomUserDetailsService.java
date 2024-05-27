package com.store.api.security;

import com.store.api.model.User;
import com.store.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username not found.");
        }
        User userModel = user.get();
        return org.springframework.security.core.userdetails.User.builder()
                .username(userModel.getUsername())
                .password(userModel.getPassword())
                .roles(getRoles(userModel))
                .build();
    }

    private String[] getRoles(User user) {
        return new String[]{user.getRole().toString()};
    }
}