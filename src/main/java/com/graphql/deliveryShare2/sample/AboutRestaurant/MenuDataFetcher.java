package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class MenuDataFetcher {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    public MenuDataFetcher(RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
      this.restaurantRepository = restaurantRepository;
      this.menuRepository = menuRepository;
    }

    public RestaurantEntity getRestaurant(MenuEntity menuEntity) {
      return restaurantRepository.findBySeq(menuEntity.getRestaurant().getSeq());
    }
  
    public DataFetcher<?> allMenues () {
      return environment -> {
        return menuRepository.findAll();
      };
    }
  
    public DataFetcher<?> Menu () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return menuRepository.findBySeq(seq);
      };
    }

    public DataFetcher<?> MenuByRes () {
      return environment -> {
        int resseq = environment.getArgument("resseq");
        return menuRepository.findByResseq(resseq);
      };
    }

  
}