package com.store.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.store.api.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtIssuer {
    private JwtProperties jwtProperties;

    @Autowired
    public JwtIssuer(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String issue(Long userId, String username, UserRole userRole) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("u", username)
                .withClaim("r", userRole.toString())
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }
}
