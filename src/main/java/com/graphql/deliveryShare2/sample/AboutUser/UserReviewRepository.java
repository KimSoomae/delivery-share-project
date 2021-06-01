package com.graphql.deliveryShare2.sample.AboutUser;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReviewEntity, Long> {
    UserReviewEntity findBySeq(int seq);
    List<UserReviewEntity> findByToseq(int toseq);
}
 