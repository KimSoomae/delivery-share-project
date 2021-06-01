package com.graphql.deliveryShare2.sample.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
    Optional<LoginUser> findByEmail(String email);
    boolean existsByEmail(String email);
    //Long findByUserSeq(Long userSeq);
}
