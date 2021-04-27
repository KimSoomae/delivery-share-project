package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class OrderDataFetcher {
    @Autowired
    private OrderRepository orderRepository;
  
    public DataFetcher<?> allOrders () {
      return environment -> {
        return orderRepository.findAll();
      };
    }
  
    public DataFetcher<?> Order () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return orderRepository.findBySeq(seq);
      };
    }
  
}
