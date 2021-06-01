package com.graphql.deliveryShare2.sample.AboutUser;

import javax.persistence.*;


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
    private double rate;

    @Column(name = "content", nullable=true)
    private String content;

    @Column(name = "to_seq", nullable=true)
    private int to_seq;
    @Column(name = "toseq", nullable=true)
    private int toseq;

    @Column(name = "fromseq", nullable=true)
    private int fromseq;

    @Column(name = "from_seq", nullable=true)
    private int from_seq;
    @ManyToOne
    @JoinColumn(name="fromseq", nullable=true, insertable=false, updatable=false )
    private UserEntity from;

    @ManyToOne
    @JoinColumn(name="toseq", nullable=true, insertable=false, updatable=false )
    private UserEntity to;

    

    public UserReviewEntity(String createdAt, String updatedAt, float rate, String content, int to_seq, int from_seq){
    public UserReviewEntity(String createdAt, String updatedAt, double rate, String content, int toseq, int fromseq){
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.rate=rate;
        this.content=content;
        this.to_seq=to_seq;
        this.from_seq=from_seq;
        this.toseq=toseq;
        this.fromseq=fromseq;
    }

    public UserEntity getUser(){
        return from;
    }

    public UserEntity getToUser(){
        return to;
    }

    public void setContent(String content){
        this.content=content;
    }

    public void setRate(double rate){
        this.rate=rate;
    }


    public void setFromseq(int fromseq){
        this.fromseq=fromseq;
    }

    public void setToseq(int toseq){
        this.toseq=toseq;
    }

}
