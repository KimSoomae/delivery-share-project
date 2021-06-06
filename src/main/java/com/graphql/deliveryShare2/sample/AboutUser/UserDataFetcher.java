package com.graphql.deliveryShare2.sample.AboutUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class UserDataFetcher {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserReviewRepository userReviewRepository;

    @Autowired
    public UserDataFetcher(UserRepository userRepository, UserReviewRepository userReviewRepository){
      this.userReviewRepository=userReviewRepository;
      this.userRepository = userRepository;
    }
  
    public DataFetcher<?> allUsers () {
      return environment -> {
        List<UserEntity> users =userRepository.findAll();
        for (int i=0; i<users.size();i++){
          UserEntity user = users.get(i);
          Double totalrate = 0.0;
          for (int j=0; j<user.getReviews().size();j++){
            Double rate = user.getReviews().get(j).getRate();
            totalrate+=rate;
          }
          totalrate /= user.getReviews().size();
          totalrate = Math.round(totalrate*10)/10.0;
          user.setRate(totalrate);
        }
        return users;
      };
    }
  
    public DataFetcher<?> User () {
      return environment -> {
        Long seq = environment.getArgument("seq");
        UserEntity User = userRepository.findBySeq(seq);
        int intseq = Long.valueOf(seq).intValue();
        List<UserReviewEntity> reviews = userReviewRepository.findByToseq(intseq);
        Double totalrate = 0.0;
        for (int i=0; i<reviews.size();i++){
          Double rate = reviews.get(i).getRate();
          totalrate += rate;
        }
        totalrate /= reviews.size();
        totalrate = Math.round(totalrate*10)/10.0;
        User.setRate(totalrate);
        return User;
      };
    }

    public DataFetcher<?> getUser () {
      return environment -> {
        Long seq = environment.getArgument("seq");
        UserEntity User = userRepository.findBySeq(seq);
        int intseq = Long.valueOf(seq).intValue();
        List<UserReviewEntity> reviews = userReviewRepository.findByToseq(intseq);
        Double totalrate = 0.0;
        for (int i=0; i<reviews.size();i++){
          Double rate = reviews.get(i).getRate();
          totalrate += rate;
        }
        totalrate /= reviews.size();
        totalrate = Math.round(totalrate*10)/10.0;
        User.setRate(totalrate);
        return User;
      };
    }
  
}
