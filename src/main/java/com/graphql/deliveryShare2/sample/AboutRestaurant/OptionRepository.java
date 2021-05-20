package com.graphql.deliveryShare2.sample.AboutRestaurant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity, Long> {
    List<OptionEntity> findByMenuseq(int menuseq);
    OptionEntity findBySeq(int seq);
}