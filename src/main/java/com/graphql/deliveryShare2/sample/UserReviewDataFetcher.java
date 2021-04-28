package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class UserReviewDataFetcher {
    @Autowired
    private UserReviewRepository userreviewRepository;
  
    public DataFetcher<?> allUserReviews () {
      return environment -> {
        return userreviewRepository.findAll();
      };
    }
  
    public DataFetcher<?> UserReview () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return userreviewRepository.findBySeq(seq);
      };
    }
  
}
