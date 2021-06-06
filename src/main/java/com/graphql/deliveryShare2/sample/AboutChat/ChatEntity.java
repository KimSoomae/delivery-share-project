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
    private Integer participant1seq;

    @Column(name = "participant2", nullable=true)
    private Integer participant2seq;

    @Column(name = "last_message", nullable=true)
    private Integer last_message;

    @Column(name = "is_active", nullable=true)
    private Boolean is_active;


    @OneToOne
    @JoinColumn(name = "last_message", nullable=true,insertable=false, updatable=false)
    private MessageEntity lastMessage;

    @ManyToOne
    @JoinColumn(name="participant1", nullable=true,insertable=false, updatable=false)
    private UserEntity participant1;

    @ManyToOne
    @JoinColumn(name="participant2", nullable=true,insertable=false, updatable=false)
    private UserEntity participant2;



    public ChatEntity(OffsetDateTime created_at, OffsetDateTime updated_at,Integer participant1seq, Integer participant2seq, Integer last_message, UserEntity participant1, UserEntity participant2, Boolean is_active){
        this.created_at=created_at;
        this.updated_at=updated_at;
        this.participant1seq = participant1seq;
        this.participant2seq = participant2seq;
        this.last_message=last_message;
        this.participant1=participant1;
        this.participant2 = participant2;
        this.is_active = is_active;
    }

    public List<MessageEntity> getMessage(){
        return messages;
    }

    public MessageEntity getLastMessage(){
        return lastMessage;
    }

    public UserEntity getParticipants1(){
        return participant1;
    }

    public UserEntity getParticipants2(){
        return participant2;
    }

    public Integer getParticipant2seq(){
        return participant2seq;
    }

    public boolean getIsActive(){
        return is_active;
    }

    public int getSeq(){
        return seq;
    }

    public void setParticipant1(int participant1seq){
        this.participant1seq=participant1seq;
    }

    public void setParticipant2(int participant2seq){
        this.participant2seq=participant2seq;
    }

    public void setLastMessage(Integer last_message){
        this.last_message=last_message;
    }

    public void setParticipant1Null(){
        this.participant1seq = (Integer)null; 
    }

    public void setIsActive(){
        this.is_active = false; 
    }

    public void setIsActive1(){
        this.is_active = true; 
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