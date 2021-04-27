package com.graphql.deliveryShare2.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionItemRepository extends JpaRepository<OptionItemEntity, Long> {
    OptionItemEntity findBySeq(int seq);
}
