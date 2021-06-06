package com.graphql.deliveryShare2.sample.AboutResReview;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;
import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantEntity;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel; 
import lombok.AllArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "res_review")
@Getter
public class ResReviewEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "createdAt", nullable=true)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable=true)
    private OffsetDateTime updated_at;

    @Column(name = "image", nullable=true)
    private String image;

    @Column(name = "content", nullable=true)
    private String content;

    @Column(name = "rate", nullable=false)
    private Double rate;

    @Column(name = "res_seq", nullable=false)
    private int resseq;

    @Column(name = "user_seq", nullable=false)
    private int user_seq;

    @ManyToOne
    @JoinColumn(name="res_seq", nullable=true,insertable=false, updatable=false)
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "review")
    private List<ImageEntity> images = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="user_seq", nullable=true, insertable=false, updatable=false)
    private UserEntity user;



    //@ManyToOne
    //@JoinColumn(name="reply_seq", nullable=true, insertable=false, updatable=false)
    //private ReplyEntity reply;

     @OneToOne(mappedBy = "review")
    private ReplyEntity reply;


    public ResReviewEntity(OffsetDateTime createdAt, OffsetDateTime updated_at, String image, String content, Double rate, int resseq, List<ImageEntity> images, ReplyEntity reply){
        this.createdAt=createdAt;
        this.updated_at=updated_at;
        this.image=image;
        this.content=content;
        this.rate = rate;
        this.resseq=resseq;
        this.images=images;
        this.reply=reply;
       
    }
    public RestaurantEntity getRestaurant(){
        return restaurant;
    }

    public RestaurantEntity getReviewCount(int resseq){
        return this.getReviewCount(resseq);
    }

    public List<ImageEntity> getImages(){
        return images;
    }

    //public ReplyEntity getReply(){
    //    return reply;
   // }

   public void setCreatedAt(){
    LocalDateTime dateTime = LocalDateTime.now();
    ZoneOffset offset = ZoneOffset.UTC;
    this.createdAt = dateTime.atOffset(offset);
}

    public void setUpdatedAt(){
    LocalDateTime dateTime = LocalDateTime.now();
    ZoneOffset offset = ZoneOffset.UTC;
    this.updated_at = dateTime.atOffset(offset);
}

public void setResSeq(int res_seq){
    this.resseq=res_seq;
}

public void setContent(String content){
    this.content=content;
}

public void setRate(Double rate){
    this.rate=rate;
}

public void setUserSeq(int user_seq){
    this.user_seq=user_seq;
}

  
}