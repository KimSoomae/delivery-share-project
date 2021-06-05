package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionItemRepository extends JpaRepository<OptionItemEntity, Long> {
    OptionItemEntity findBySeq(int seq);
    //OptionItemEntity findByOptionItemSeq(int optionitemseq);
    OptionItemEntity findByPriceAndContentAndOption_seq(int price, String content, int option_seq);
}