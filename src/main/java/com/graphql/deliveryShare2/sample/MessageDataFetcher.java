package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class MessageDataFetcher {
    @Autowired
    private MessageRepository messageRepository;
  
    public DataFetcher<?> allMessages () {
      return environment -> {
        return messageRepository.findAll();
      };
    }
  
    public DataFetcher<?> Message () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return messageRepository.findBySeq(seq);
      };
    }
  
}
