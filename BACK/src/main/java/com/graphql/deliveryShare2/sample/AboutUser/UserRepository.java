package com.graphql.deliveryShare2.sample.AboutUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findBySeq(Long seq);
    UserEntity findByID(String ID);
}
