package com.graphql.deliveryShare2.sample.AboutUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    ReportEntity findBySeq(int seq);
    List<ReportEntity> findByChatseq(int chatseq);
}
