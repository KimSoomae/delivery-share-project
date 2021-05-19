package com.graphql.deliveryShare2.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallingRepository extends JpaRepository<CallingEntity, Long>, CallingCustomRepository {
    CallingEntity findBySeq(int seq);
}
