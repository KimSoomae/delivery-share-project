package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import java.util.List;
import java.util.ArrayList;
@Component
public class RestaurantDataFetcher {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RunTimeRepository runTimeRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    public RestaurantDataFetcher(RestaurantRepository restaurantRepository, RunTimeRepository runTimeRepository, MenuRepository menuRepository, OptionRepository optionRepository){
      this.restaurantRepository=restaurantRepository;
      this.runTimeRepository=runTimeRepository;
      this.menuRepository = menuRepository;
      this.optionRepository=optionRepository;
    }

    public RunTimeEntity getRunTime(RestaurantEntity restaurantEntity){
      return runTimeRepository.findBySeq(restaurantEntity.getRunTime().getSeq());

    }


    public List<OptionEntity> getOptions(RestaurantEntity restaurantEntity) {
      return optionRepository.findAll();
      

    }
    public MenuEntity getMenu(RestaurantEntity restaurantEntity) {
      return menuRepository.findBySeq(restaurantEntity.getMenu().getSeq());
    }


    public DataFetcher<?> allRestaurants () {
      return environment -> {
        return restaurantRepository.findAll();
      };
    }
  
    public DataFetcher<?> getRestaurants () {
      return environment -> {
        String category = environment.getArgument("category");
        String si = environment.getArgument("si");
        String dong = environment.getArgument("dong");
        return restaurantRepository.getPossibleRestaurants(category,si,dong); 
      };
    }

    public DataFetcher<?> getRestaurant () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return restaurantRepository.getRestaurant(seq); 
      };
    }

    public DataFetcher<?> getLikedRestaurants() {
      return environment -> {
        int userseq = environment.getArgument("userseq");
        //RestaurantEntity restaurantEntity = new RestaurantEntity();
        List<RestaurantEntity> likedR = new ArrayList<RestaurantEntity>();
        List<LikesEntity> likes = likesRepository.findAllByUserseq(userseq);
        System.out.println("라이크"+likes);
        for (int i=0; i<likes.size();i++){
            int idx = likes.get(i).getResseq();
            System.out.println("인덱스"+idx);
            System.out.println("레스"+restaurantRepository.findBySeq(idx));
            likedR.add(restaurantRepository.findBySeq(idx));
        }
  
        return likedR;
      };
    }
   
   
 
}