package com.graphql.deliveryShare2.sample.AboutResReview;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "reply")
@Getter
public class ReplyEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "content",nullable=false)
    private String content;

    @Column(name = "created_at", nullable=false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable=true)
    private OffsetDateTime updatedAt;

    @Column(name = "review_seq", nullable=false)
    private int reviewSeq;

    @OneToOne
    @JoinColumn(name="review_seq", nullable=true, insertable=false, updatable=false)
    private ResReviewEntity review;

    public ReplyEntity(String content){
        this.content=content;
    }

    public void setContent(String content){
        this.content=content;
    }

    public void setReviewSeq(int reviewSeq){
        this.reviewSeq=reviewSeq;
    }
    public void setCreatedAt(){
        LocalDateTime dateTime = LocalDateTime.now();
        ZoneOffset offset = ZoneOffset.UTC;
        this.createdAt = dateTime.atOffset(offset);
    }
    public void setUpdatedAt(){
        LocalDateTime dateTime = LocalDateTime.now();
        ZoneOffset offset = ZoneOffset.UTC;
        this.updatedAt = dateTime.atOffset(offset);
    }
   
}
