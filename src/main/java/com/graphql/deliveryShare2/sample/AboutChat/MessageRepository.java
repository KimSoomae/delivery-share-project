package com.graphql.deliveryShare2.sample.AboutChat;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    MessageEntity findBySeq(int seq);

}