package com.graphql.deliveryShare2.sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallLocationRepository extends JpaRepository<CallLocationEntity, Long> {
    CallLocationEntity findBySeq(int seq);
}
