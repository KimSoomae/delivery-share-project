package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class CallLocationDataFetcher {
    @Autowired
    private CallLocationRepository callLocationRepository;
  
    public DataFetcher<?> allCallLocations () {
      return environment -> {
        return callLocationRepository.findAll();
      };
    }
  
    public DataFetcher<?> CallLocation () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return callLocationRepository.findBySeq(seq);
      };
    }
  
}
