package com.graphql.deliveryShare2.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    CartEntity findBySeq(int seq);
    Integer deleteBySeq(int seq);
}
