package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class SelectedMenuDataFetcher {
    @Autowired
    private SelectedMenuRepository selectedmenuRepository;
  
    public DataFetcher<?> allSelectedMenus () {
      return environment -> {
        return selectedmenuRepository.findAll();
      };
    }
  
    public DataFetcher<?> SelectedMenu () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return selectedmenuRepository.findBySeq(seq);
      };
    }
  
}
