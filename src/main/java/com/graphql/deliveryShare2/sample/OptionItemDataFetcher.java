package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class OptionItemDataFetcher {
    @Autowired
    private OptionItemRepository optionItemRepository;
  
    public DataFetcher<?> allOptionItems () {
      return environment -> {
        return optionItemRepository.findAll();
      };
    }
  
    public DataFetcher<?> OptionItem () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return optionItemRepository.findBySeq(seq);
      };
    }
  
}
