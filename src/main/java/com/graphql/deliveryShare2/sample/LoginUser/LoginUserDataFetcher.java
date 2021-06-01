package com.graphql.deliveryShare2.sample.LoginUser;

import graphql.schema.DataFetcher;
import com.graphql.deliveryShare2.config.security.*;
import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
@Component
@RequiredArgsConstructor
public class LoginUserDataFetcher {
    @Autowired
    LoginUserService service;

    private final AuthenticationProvider authenticationProvider;

    //@PreAuthorize("isAuthenticated()")
    public String getToken(LoginUser loginUser) {
        return service.getToken(loginUser);
    }


    //@PreAuthorize("isAnonymous()")
    //public LoginUser login(String email, String password) {
    //    UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(email, password);
    //    try {
    //        SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(credentials));
    //        return service.getCurrentUser();
    //    } catch (AuthenticationException ex) {
    //        throw new BadCredentialsException(email);
    //    }
    //}

    //public DataFetcher<?> getCurrentUser(){
    //    return environment -> {
    //         return service.getCurrentUser();
    //    };
    //}

    //@PreAuthorize("isAnonymous()")
    public DataFetcher<?> login () {
        return environment -> {
            String email=environment.getArgument("email");
            String password=environment.getArgument("password");
            UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(email, password);
            System.out.println("이메일"+email+"패스워드"+password+"크레덴셜"+credentials);
            try {
                System.out.println("여기들어왔나1");
                System.out.println(authenticationProvider.authenticate(credentials));
                System.out.println("여기들어왔나2");
                
                SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(credentials));
                System.out.println("여기들어왔나");
                System.out.println(authenticationProvider.authenticate(credentials));
                return service.getCurrentUser();
            } catch (AuthenticationException ex) {
                String eemail = environment.getArgument("email");
                throw new BadCredentialsException(eemail);
            }
        };
      }

     
}