package com.graphql.deliveryShare2.sample.AboutUser;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.deliveryShare2.sample.AboutCart.CartEntity;
import com.graphql.deliveryShare2.sample.AboutCart.CartRepository;
import com.graphql.deliveryShare2.sample.AboutCall.OrderEntity;
import com.graphql.deliveryShare2.sample.AboutCall.OrderRepository;
import com.graphql.deliveryShare2.sample.AboutCall.CallingEntity;
import com.graphql.deliveryShare2.sample.AboutCall.CallingRepository;
import com.graphql.deliveryShare2.sample.AboutResReview.ResReviewEntity;
import com.graphql.deliveryShare2.sample.AboutResReview.ResReviewRepository;

import graphql.schema.DataFetcher;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class UserDataFetcher {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserReviewRepository userReviewRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CallingRepository callingRepository;

    @Autowired
    private ResReviewRepository resReviewRepository;
    
    @Autowired
    public UserDataFetcher(UserRepository userRepository, UserReviewRepository userReviewRepository, CartRepository cartRepository, OrderRepository orderRepository, CallingRepository callingRepository, ResReviewRepository resReviewRepository){
      this.userReviewRepository=userReviewRepository;
      this.userRepository = userRepository;
      this.cartRepository = cartRepository;
      this.orderRepository = orderRepository;
      this.callingRepository = callingRepository;
      this.resReviewRepository = resReviewRepository;
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

        List<CartEntity> carts = User.getCarts();
        List<ResReviewEntity> resReviews = User.getResReviews();
        int totalorders = 0;

        //true면 3일이내 주문 중에 식당리뷰 작성 안 한 것 존재 
        Boolean canwriteR = User.getCanWriteReview();
        
        OffsetDateTime now = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("+9"));
        OffsetDateTime before3 = now.minusDays(3);
        
        for(int i = 0; i<carts.size();i++){
          int callseq = carts.get(i).getCallseq();
          CallingEntity call = callingRepository.findBySeq(callseq); 
          
          OrderEntity order = orderRepository.findByCall_seq(callseq);
          if(order != null){
            if(order.getStatus().equals("completed")){
              totalorders += 1;
              OffsetDateTime at = order.getCreated_at();
              if(before3.isBefore(at) && (order.getCanWriteResReview() == true)){
                //if(reviews)
                User.setCanWriteReview(true);
              }
            }   
          }
        }
        User.setOrderCounts(totalorders);

        return User;
      };
    }
  
}
    
