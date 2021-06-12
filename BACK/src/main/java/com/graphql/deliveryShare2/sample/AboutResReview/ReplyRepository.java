package com.graphql.deliveryShare2.sample.AboutResReview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.SortOrder;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    ReplyEntity findBySeq(int seq);
}
