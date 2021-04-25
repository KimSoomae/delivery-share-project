package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class UserDataFetcher {
    @Autowired
    private UserRepository userRepository;
  
    public DataFetcher<?> allUsers () {
      return environment -> {
        return userRepository.findAll();
      };
    }
  
    public DataFetcher<?> User () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return userRepository.findBySeq(seq);
      };
    }
  
}
