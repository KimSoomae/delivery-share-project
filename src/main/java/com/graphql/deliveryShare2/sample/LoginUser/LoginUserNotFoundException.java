package com.graphql.deliveryShare2.sample.LoginUser;

import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;

@RequiredArgsConstructor
public class LoginUserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -4197339452705364933L;
    private final Long seq;

    @Override
    public String getMessage() {
        return MessageFormat.format("User with ID ''{0}'' isn''t available", seq);
    }
}