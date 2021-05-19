package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.graphql.deliveryShare2.sample.CallingEntity;
import com.graphql.deliveryShare2.sample.UserEntity;
import com.graphql.deliveryShare2.sample.UserRepository;
import graphql.schema.DataFetcher;


@Component
public class CallingDataFetcher {
    @Autowired
    private CallingRepository callingRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CallLocationRepository callLocationRepository;

   
    @Autowired
    public CallingDataFetcher(CallingRepository callingRepository, UserRepository userRepository, RestaurantRepository restaurantRepository, CallLocationRepository callLocationRepository){
      this.callingRepository=callingRepository;
      this.userRepository=userRepository;
      this.restaurantRepository=restaurantRepository;
      this.callLocationRepository=callLocationRepository;
   
    }

    public DataFetcher<?> allCallings () {
      return environment -> {
        return callingRepository.findAll();
      };
    }
  
    public DataFetcher<?> Calling () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return callingRepository.findBySeq(seq);
      };
    }

    public DataFetcher<?> getNearCalls (){
      return environment -> {
          return callingRepository.getNearCalls();
      };
  }

    public UserEntity getUser(CallingEntity callingEntity){
      return userRepository.findBySeq(callingEntity.getUser().getSeq());
    }

    public RestaurantEntity getRestaurant(CallingEntity callingEntity){
      return restaurantRepository.findBySeq(callingEntity.getRestaurant().getSeq());
    }

    public CallLocationEntity getCallLocation(CallingEntity callingEntity){
      return callLocationRepository.findBySeq(callingEntity.getCallLocation().getSeq());
    }

  
}
