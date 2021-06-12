package com.graphql.deliveryShare2.sample.AboutRestaurant;

import javax.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface LikesRepository extends JpaRepository<LikesEntity, Long>{
    Long countByResseqAndUserseq(int resseq, int userseq);
    LikesEntity findByResseqAndUserseq(int resseq, int userseq);
    Integer deleteBySeq(int seq);
    List<LikesEntity> findAllByUserseq(int userseq);
}
