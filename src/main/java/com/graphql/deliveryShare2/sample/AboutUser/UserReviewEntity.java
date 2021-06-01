package com.graphql.deliveryShare2.sample.AboutUser;

import javax.persistence.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

<<<<<<< HEAD
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
=======
    @Column(name = "toseq", nullable=true)
    private int toseq;

    @Column(name = "fromseq", nullable=true)
    private int fromseq;

    @ManyToOne
    @JoinColumn(name="fromseq", nullable=true, insertable=false, updatable=false )
    private UserEntity user;
>>>>>>> be98061299c2c7c0b88d22403b95aa2413d752ee

    @ManyToOne
    @JoinColumn(name="toseq", nullable=true, insertable=false, updatable=false )
    private UserEntity to;


<<<<<<< HEAD
    public UserReviewEntity(String createdAt, String updatedAt, float rate, String content, int to_seq, int from_seq){
    public UserReviewEntity(String createdAt, String updatedAt, double rate, String content, int toseq, int fromseq){
=======

    public UserReviewEntity(String createdAt, String updatedAt, float rate, String content, int toseq, int fromseq){
>>>>>>> be98061299c2c7c0b88d22403b95aa2413d752ee
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.rate=rate;
        this.content=content;
<<<<<<< HEAD
        this.to_seq=to_seq;
        this.from_seq=from_seq;
=======
>>>>>>> be98061299c2c7c0b88d22403b95aa2413d752ee
        this.toseq=toseq;
        this.fromseq=fromseq;
    }

    public UserEntity getUser(){
<<<<<<< HEAD
        return from;
=======
        return user;
>>>>>>> be98061299c2c7c0b88d22403b95aa2413d752ee
    }

    public UserEntity getToUser(){
        return to;
    }

    public void setContent(String content){
        this.content=content;
    }

<<<<<<< HEAD
    public void setRate(double rate){
=======
    public void setRate(float rate){
>>>>>>> be98061299c2c7c0b88d22403b95aa2413d752ee
        this.rate=rate;
    }


    public void setFromseq(int fromseq){
        this.fromseq=fromseq;
    }

    public void setToseq(int toseq){
        this.toseq=toseq;
    }

}


