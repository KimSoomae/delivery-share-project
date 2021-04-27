package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class RestaurantDataFetcher {
    @Autowired
    private RestaurantRepository restaurantRepository;
  
    public DataFetcher<?> allRestaurants () {
      return environment -> {
        return restaurantRepository.findAll();
      };
    }
  
    public DataFetcher<?> Restaurant () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return restaurantRepository.findBySeq(seq);
      };
    }
  
}
