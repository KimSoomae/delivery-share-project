package com.graphql.deliveryShare2.sample.AboutCall;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional 
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findBySeq(int seq);
    OrderEntity findByCall_seq(int call_seq);
}
