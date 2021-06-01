package com.graphql.deliveryShare2.sample.AboutResReview;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import java.io.Serializable;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "review_image")
@Getter
public class ImageEntity implements Serializable {
    
    @Id
    @Column(name = "seq",nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "image",nullable=false)
    private String image;

    @Column(name = "review_seq", nullable=false)
    private int review_seq;

    @ManyToOne
    @JoinColumn(name="review_seq", nullable=true, insertable=false, updatable=false)
    private ResReviewEntity review;

}
