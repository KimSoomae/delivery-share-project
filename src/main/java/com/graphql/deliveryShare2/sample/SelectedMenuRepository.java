package com.graphql.deliveryShare2.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedMenuRepository extends JpaRepository<SelectedMenuEntity, Long> {
    SelectedMenuEntity findBySeq(int seq);
}
