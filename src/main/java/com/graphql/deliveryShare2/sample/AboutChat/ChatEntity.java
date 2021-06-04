package com.graphql.deliveryShare2.sample.AboutChat;

import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;

import javax.persistence.*;

import java.util.List;
import java.util.ArrayList;


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
@Table(name = "chat")
@Getter
public class ChatEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "created_at",nullable=false)
    private String created_at;

    @Column(name = "updated_at", nullable=false)
    private String updated_at;

    @OneToMany(mappedBy = "chat")
    private List<MessageEntity> messages = new ArrayList<>();

    @OneToMany(mappedBy = "chat")
    private List<UserEntity> participants = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="last_message", nullable=true, insertable=false, updatable=false )
    private MessageEntity lastMessage;

    public ChatEntity(String created_at, String updated_at){
        this.created_at=created_at;
        this.updated_at=updated_at;
    }

    public List<MessageEntity> getMessage(){
        return messages;
    }

    public List<UserEntity> getUser(){
        return participants;
    }

    public MessageEntity getLastMessage(){
        return lastMessage;
    }
}