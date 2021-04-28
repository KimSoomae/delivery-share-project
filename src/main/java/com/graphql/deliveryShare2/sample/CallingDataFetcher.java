package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class CallingDataFetcher {
    @Autowired
    private CallingRepository callingRepository;
  
    public DataFetcher<?> allCallings () {
      return environment -> {
        return callingRepository.findAll();
      };
    }
  
    public DataFetcher<?> Calling () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return callingRepository.findBySeq(seq);
      };
    }
  
}
