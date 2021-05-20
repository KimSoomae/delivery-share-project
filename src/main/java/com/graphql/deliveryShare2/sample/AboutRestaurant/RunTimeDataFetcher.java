package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class RunTimeDataFetcher {
    @Autowired
    private RunTimeRepository runtimeRepository;
  
    public DataFetcher<?> allRunTimes () {
      return environment -> {
        return runtimeRepository.findAll();
      };
    }
  
    public DataFetcher<?> RunTime () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return runtimeRepository.findBySeq(seq);
      };
    }
  
}
