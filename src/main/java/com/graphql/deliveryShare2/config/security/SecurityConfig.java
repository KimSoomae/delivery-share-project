package com.graphql.deliveryShare2.config.security;

import org.springframework.context.annotation.Bean;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.graphql.deliveryShare2.sample.AboutUser.*;
import com.graphql.deliveryShare2.sample.LoginUser.*;
@Configuration
public class SecurityConfig {
    @Bean
    public Algorithm jwtAlgorithm(SecurityProperties properties) {
        return Algorithm.HMAC256(properties.getTokenSecret());
    }

    @Bean
    public JWTVerifier verifier(SecurityProperties properties, Algorithm algorithm) {
        return JWT
            .require(algorithm)
            .withIssuer(properties.getTokenIssuer())
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(SecurityProperties properties) {
        return new BCryptPasswordEncoder(properties.getPasswordStrength());
    }


    @Bean
    public AuthenticationProvider authenticationProvider(LoginUserService loginUserService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(loginUserService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
