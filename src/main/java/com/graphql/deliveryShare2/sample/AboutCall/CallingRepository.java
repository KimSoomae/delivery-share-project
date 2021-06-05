package com.graphql.deliveryShare2.sample.AboutCall;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.TimerTask;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional 
public interface CallingRepository extends JpaRepository<CallingEntity, Long>, CallingCustomRepository {
    CallingEntity findBySeq(int seq);
    List<CallingEntity> findAllByUser_seq(Long user_seq);
    Integer deleteBySeq(int seq);
    Integer deleteBySeqAndStatus(int seq, String status);
    //TimerTask deleteBySeqTimerTask(int seq);
}
