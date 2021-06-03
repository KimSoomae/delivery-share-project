package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeliverylocRepository extends JpaRepository<DeliverylocEntity, Long>{
    List <DeliverylocEntity> findAllBySiAndDong(String si, String dong);
}
