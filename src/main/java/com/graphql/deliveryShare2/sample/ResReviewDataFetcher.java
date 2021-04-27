package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class ResReviewDataFetcher {
    @Autowired
    private ResReviewRepository resreviewRepository;
  
    public DataFetcher<?> allResReviews () {
      return environment -> {
        return resreviewRepository.findAll();
      };
    }
  
    public DataFetcher<?> ResReview () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return resreviewRepository.findBySeq(seq);
      };
    }
  
}
