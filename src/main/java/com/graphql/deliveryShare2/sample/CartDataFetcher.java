package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class CartDataFetcher {
    @Autowired
    private CartRepository cartRepository;
  
    public DataFetcher<?> allCarts () {
      return environment -> {
        return cartRepository.findAll();
      };
    }
  
    public DataFetcher<?> Cart () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return cartRepository.findBySeq(seq);
      };
    }
  
}
