package com.graphql.deliveryShare2.sample.AboutRestaurant;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    List<MenuEntity> findByResseq(int resseq);
    MenuEntity findBySeq(int seq);
}