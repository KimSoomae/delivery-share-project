package com.graphql.deliveryShare2.sample.AboutCart;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SelectedOptionRepository extends JpaRepository<SelectedOptionEntity, Integer> {
    SelectedOptionEntity findBySeq(int seq);

    //SelectedOptionEntity findBySelectedMenuSeq(int selectedmenu_seq);

    
}
