package com.store.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

    private JwtProperties jwtProperties;

    @Autowired
    public JwtDecoder(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    public DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC256(jwtProperties.getSecretKey()))
                .build()
                .verify(token);
    }
}
