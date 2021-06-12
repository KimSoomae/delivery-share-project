package com.graphql.deliveryShare2.sample.AboutCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    CartEntity findBySeq(int seq);
    Integer deleteBySeq(int seq);
    List<CartEntity> findAllByCall_seq(int callseq);
    List<CartEntity> findByUserSeq(int userSeq);
}
