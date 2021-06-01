package com.graphql.deliveryShare2.sample.LoginUser;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(onConstructor = @__(@JsonCreator))
public class CreateLoginUserInput {
    private final String email;
    private final String password;
}
