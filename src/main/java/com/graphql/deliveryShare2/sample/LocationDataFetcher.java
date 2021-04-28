package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class LocationDataFetcher {
    @Autowired
    private LocationRepository locationRepository;
  
    public DataFetcher<?> allLocations () {
      return environment -> {
        return locationRepository.findAll();
      };
    }
  
    public DataFetcher<?> Location () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return locationRepository.findBySeq(seq);
      };
    }
  
}
