package com.graphql.deliveryShare2.sample.AboutCart;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SelectedMenuRepository extends JpaRepository<SelectedMenuEntity, Integer> {
    SelectedMenuEntity findBySeq(int seq);
    Integer deleteBySeq(int seq);
}
