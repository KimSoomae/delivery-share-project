package com.graphql.deliveryShare2.sample.AboutResReview;

import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantEntity;
import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class ResReviewDataFetcher {
    @Autowired
    private ResReviewRepository resReviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    public ResReviewDataFetcher(RestaurantRepository restaurantRepository, ResReviewRepository resReviewRepository){
      this.restaurantRepository=restaurantRepository;
      this.resReviewRepository=resReviewRepository;
    }
    public DataFetcher<?> allResReviews () {
      return environment -> {
        return resReviewRepository.findAll();
      };
    }
  
    public DataFetcher<?> ResReview () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return resReviewRepository.findBySeq(seq);
      };
    }

    public RestaurantEntity getRestaurant(ResReviewEntity resReviewEntity){
      return restaurantRepository.findBySeq(resReviewEntity.getRestaurant().getSeq());
    }

    public DataFetcher<?> getResReviews () {
      return environment -> {
        int resseq = environment.getArgument("resseq");
        return resReviewRepository.findByResseq(resseq);
      };
    }

    public DataFetcher<?> getReviewCount () {
      return environment -> {
        int resseq = environment.getArgument("resseq");
        return resReviewRepository.getReviewCount(resseq);
      };
    }

    

  
}
