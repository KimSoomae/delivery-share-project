package com.graphql.deliveryShare2.sample;

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
@Table(name = "res_review")
@Getter
public class ResReviewEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;



    @Column(name = "createdAt", nullable=false)
    private String createdAt;

    @Column(name = "updatedAt", nullable=true)
    private String updatedAt;

    @Column(name = "image", nullable=true)
    private String image;

    @Column(name = "content", nullable=true)
    private String content;

    @Column(name = "rate", nullable=false)
    private float rate;

    public ResReviewEntity(String createdAt, String updatedAt, String image, String content, float rate){
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.image=image;
        this.content=content;
        this.rate = rate;
    }

}
