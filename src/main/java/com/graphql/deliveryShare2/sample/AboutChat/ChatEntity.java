package com.graphql.deliveryShare2.sample.AboutChat;

import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;
import com.graphql.deliveryShare2.sample.AboutChat.MessageEntity;

import javax.persistence.*;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;



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

    @Column(name = "created_at", nullable=true)
    private OffsetDateTime created_at;

    @Column(name = "updated_at", nullable=true)
    private OffsetDateTime updated_at;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.REMOVE)
    private List<MessageEntity> messages = new ArrayList<>();

    @Column(name = "participant1", nullable=true)
    private int participant1;

    @Column(name = "participant2", nullable=true)
    private int participant2;

    @Column(name = "last_message", nullable=true)
    private Integer last_message;


    @OneToOne
    @JoinColumn(name = "last_message", nullable=true,insertable=false, updatable=false)
    private MessageEntity lastMessage;

    @ManyToOne
    @JoinColumn(name="participant1", nullable=true,insertable=false, updatable=false)
    private UserEntity participants1;

    @ManyToOne
    @JoinColumn(name="participant2", nullable=true,insertable=false, updatable=false)
    private UserEntity participants2;



    public ChatEntity(OffsetDateTime created_at, OffsetDateTime updated_at,int participant1, int participant2, Integer last_message, UserEntity participants1, UserEntity participants2){
        this.created_at=created_at;
        this.updated_at=updated_at;
        this.participant1 = participant1;
        this.participant2 = participant2;
        this.last_message=last_message;
        this.participants1=participants1;
        this.participants2 = participants2;
    }

    public List<MessageEntity> getMessage(){
        return messages;
    }

    public MessageEntity getLastMessage(){
        return lastMessage;
    }

    public UserEntity getParticipants1(){
        return participants1;
    }

    public UserEntity getParticipants2(){
        return participants2;
    }

   

    public void setParticipant1(int participant1){
        this.participant1=participant1;
    }

    public void setParticipant2(int participant2){
        this.participant2=participant2;
    }

    public void setLastMessage(Integer last_message){
        this.last_message=last_message;
    }

    public void setCreatedAt(){
        LocalDateTime dateTime = LocalDateTime.now();
        ZoneOffset offset = ZoneOffset.UTC;
        this.created_at = dateTime.atOffset(offset);
    }
    public void setUpdatedAt(){
        LocalDateTime dateTime = LocalDateTime.now();
        ZoneOffset offset = ZoneOffset.UTC;
        this.updated_at = dateTime.atOffset(offset);
    }

    
}