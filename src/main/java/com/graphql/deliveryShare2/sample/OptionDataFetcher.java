package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class OptionDataFetcher {
    @Autowired
    private OptionRepository optionRepository;
  
    public DataFetcher<?> allOptions () {
      return environment -> {
        return optionRepository.findAll();
      };
    }
  
    public DataFetcher<?> Option () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return optionRepository.findBySeq(seq);
      };
    }
  
}
