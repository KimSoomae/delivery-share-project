package com.graphql.deliveryShare2.sample.AboutUser;

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
        Long seq = environment.getArgument("seq");
        return userRepository.findBySeq(seq);
      };
    }

    public DataFetcher<?> getUser () {
      return environment -> {
        Long seq = environment.getArgument("seq");
        
        return userRepository.findBySeq(seq);
      };
    }
  
}
