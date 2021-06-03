package com.graphql.deliveryShare2.sample.AboutResReview;

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

    @Column(name = "createdAt", nullable=false)
    private OffsetDateTime createdAt;

    @Column(name = "updatedAt", nullable=true)
    private OffsetDateTime updatedAt;

    @Column(name = "image", nullable=true)
    private String image;

    @Column(name = "content", nullable=true)
    private String content;

    @Column(name = "rate", nullable=false)
    private float rate;

    @Column(name = "res_seq", nullable=false)
    private int resseq;

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

    @OneToMany(mappedBy = "review")
    private List<ReplyEntity> reply = new ArrayList<>();

    public ResReviewEntity(OffsetDateTime createdAt, OffsetDateTime updatedAt, String image, String content, float rate, int resseq, List<ImageEntity> images, List<ReplyEntity> reply){
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
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

    public List<ReplyEntity> getReplies(){
        return reply;
    }
  
}
