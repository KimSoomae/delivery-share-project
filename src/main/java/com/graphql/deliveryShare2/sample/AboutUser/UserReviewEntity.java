package com.graphql.deliveryShare2.sample.AboutUser;

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

    @Column(name = "content", nullable=true)
    private String content;

    @Column(name = "toseq", nullable=true)
    private int toseq;

    @Column(name = "fromseq", nullable=true)
    private int fromseq;

    @ManyToOne
    @JoinColumn(name="fromseq", nullable=true, insertable=false, updatable=false )
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="toseq", nullable=true, insertable=false, updatable=false )
    private UserEntity to;



    public UserReviewEntity(String createdAt, String updatedAt, float rate, String content, int toseq, int fromseq){
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.rate=rate;
        this.content=content;
        this.toseq=toseq;
        this.fromseq=fromseq;
    }

    public UserEntity getUser(){
        return user;
    }

    public UserEntity getToUser(){
        return to;
    }

    public void setContent(String content){
        this.content=content;
    }

    public void setRate(float rate){
        this.rate=rate;
    }


    public void setFromseq(int fromseq){
        this.fromseq=fromseq;
    }

    public void setToseq(int toseq){
        this.toseq=toseq;
    }

}


