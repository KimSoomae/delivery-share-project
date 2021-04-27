package com.graphql.deliveryShare2.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunTimeRepository extends JpaRepository<RunTimeEntity, Long> {
    RunTimeEntity findBySeq(int seq);
}
