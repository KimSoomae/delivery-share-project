package com.graphql.deliveryShare2.sample.AboutChat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class ChatDataFetcher {
    @Autowired
    private ChatRepository chatRepository;
  
    public DataFetcher<?> allChats () {
      return environment -> {
        return chatRepository.findAll();
      };
    }
  
    public DataFetcher<?> Chat () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return chatRepository.findBySeq(seq);
      };
    }
  
}
