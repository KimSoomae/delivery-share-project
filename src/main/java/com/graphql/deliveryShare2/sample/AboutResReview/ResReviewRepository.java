package com.graphql.deliveryShare2.sample.AboutResReview;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResReviewRepository extends JpaRepository<ResReviewEntity, Long>, ResReviewCustomRepository {
    List<ResReviewEntity> findByResseq(int resseq);
    ResReviewEntity findBySeq(int seq);
}