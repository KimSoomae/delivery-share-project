package com.graphql.deliveryShare2.sample.AboutUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "user_review")
@Getter
public class UserReviewEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    

    @Column(name = "createdAt", nullable=false)
    private String createdAt;

    @Column(name = "updatedAt", nullable=true)
    private String updatedAt;

    @Column(name = "rate", nullable=true)
    private float rate;

    @Column(name = "content", nullable=true)
    private String content;

    @Column(name = "to_seq", nullable=true)
    private int to_seq;

    @Column(name = "from_seq", nullable=true)
    private int from_seq;

    

    public UserReviewEntity(String createdAt, String updatedAt, float rate, String content, int to_seq, int from_seq){
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.rate=rate;
        this.content=content;
        this.to_seq=to_seq;
        this.from_seq=from_seq;
    }

}
