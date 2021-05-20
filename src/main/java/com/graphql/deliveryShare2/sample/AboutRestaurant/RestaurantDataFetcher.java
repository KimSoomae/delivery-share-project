package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class RestaurantDataFetcher {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RunTimeRepository runTimeRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    public RestaurantDataFetcher(RestaurantRepository restaurantRepository, RunTimeRepository runTimeRepository, MenuRepository menuRepository){
      this.restaurantRepository=restaurantRepository;
      this.runTimeRepository=runTimeRepository;
      this.menuRepository = menuRepository;
    }

    public RunTimeEntity getRunTime(RestaurantEntity restaurantEntity){
      return runTimeRepository.findBySeq(restaurantEntity.getRunTime().getSeq());

    }

    public MenuEntity getMenu(RestaurantEntity restaurantEntity) {
      return menuRepository.findBySeq(restaurantEntity.getMenu().getSeq());
    }


    public DataFetcher<?> allRestaurants () {
      return environment -> {
        return restaurantRepository.findAll();
      };
    }
  
    public DataFetcher<?> Restaurant () {
      return environment -> {
        String category = environment.getArgument("category");
        int isopen = environment.getArgument("isopen");
        return restaurantRepository.findByCategoryAndIsopen(category,isopen); 
      };
    }

    public DataFetcher<?> RestaurantBySeq () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return restaurantRepository.findBySeq(seq); 
      };
    }

   
 
}