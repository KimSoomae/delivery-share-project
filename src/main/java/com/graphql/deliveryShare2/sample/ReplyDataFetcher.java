package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class ReplyDataFetcher {
    @Autowired
    private ReplyRepository replyRepository;
  
    public DataFetcher<?> allReplies () {
      return environment -> {
        return replyRepository.findAll();
      };
    }
  
    public DataFetcher<?> Reply () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return replyRepository.findBySeq(seq);
      };
    }
  
}
