package com.graphql.deliveryShare2.sample.AboutRestaurant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long>, RestaurantCustomRepository {
    
    List<RestaurantEntity> findByCategoryAndIsopen(String category, int isopen);
    RestaurantEntity findBySeq(int seq);

}