package com.graphql.deliveryShare2.sample.AboutChat;

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
@Table(name = "chat")
@Getter
public class ChatEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "createdAt",nullable=false)
    private String createdAt;

    @Column(name = "updatedAt", nullable=false)
    private String updatedAt;

    public ChatEntity(String createdAt, String updatedAt){
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }
}
