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
@Table(name = "reply")
@Getter
public class ReplyEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "content",nullable=false)
    private String content;
    //@Column(name = "createdAt", nullable=false)
    //private String createdAt;

    //@Column(name = "updatedAt", nullable=false)
    //private String updatedAt;


    public ReplyEntity(String content){
        this.content=content;
    }
}
