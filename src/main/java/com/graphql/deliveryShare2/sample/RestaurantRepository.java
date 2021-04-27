package com.graphql.deliveryShare2.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    RestaurantEntity findBySeq(int seq);
}
