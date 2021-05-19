package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class MenuDataFetcher {
    @Autowired
    private MenuRepository menuRepository;
  
    public DataFetcher<?> allMenues () {
      return environment -> {
        return menuRepository.findAll();
      };
    }
  
    public DataFetcher<?> Menu () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return menuRepository.findBySeq(seq);
      };
    }
  
}
