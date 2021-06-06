package com.graphql.deliveryShare2.sample.AboutUser;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserReviewRepository extends JpaRepository<UserReviewEntity, Integer> {
    UserReviewEntity findBySeq(int seq);
    List<UserReviewEntity> findByToseq(int toseq);
    Integer deleteBySeq(int seq);
    List<UserReviewEntity> findAllByToseq(Long toseq);
}