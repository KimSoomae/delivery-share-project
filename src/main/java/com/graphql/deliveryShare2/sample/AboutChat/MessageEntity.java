package com.graphql.deliveryShare2.sample.AboutChat;

import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;

import javax.persistence.*;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;


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
@Table(name = "message")
@Getter
public class MessageEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    
    //@Column(name = "isRead", nullable=false)
    //private Boolean isRead;

    @Column(name = "is_read", nullable=true)
    private Boolean is_read;

    @Column(name = "created_at", nullable=true)
    private OffsetDateTime created_at;

    @Column(name = "updated_at", nullable=true)
    private OffsetDateTime updated_at;

    @Column(name = "text",nullable=true)
    private String text;
 
    @Column(name = "toseq", nullable=true)
    private int toseq;


    @Column(name = "fromseq", nullable=true)
    private int fromseq;

    @Column(name = "chat_seq", nullable=true)
    private int chat_seq;

    

    @ManyToOne
    @JoinColumn(name="fromseq", nullable=true, insertable=false, updatable=false )
    private UserEntity from;

    @ManyToOne
    @JoinColumn(name="toseq", nullable=true, insertable=false, updatable=false )
    private UserEntity to;

    @ManyToOne
    @JoinColumn(name="chat_seq", nullable=true, insertable=false, updatable=false)
    private ChatEntity chat;


    public MessageEntity(Boolean is_read, OffsetDateTime created_at, OffsetDateTime updated_at, String text, int fromseq, int toseq, UserEntity from,  UserEntity to, int chat_seq){
        this.is_read=is_read;
        this.created_at=created_at;
        this.updated_at=updated_at;
        this.text = text;
        this.fromseq =fromseq;
        this.toseq = toseq;
        this.from = from;
        this.to = to;
        this.chat_seq=chat_seq;
    }

    public UserEntity getFromUser(){
        return from;
    }

    public UserEntity getToUser(){
        return to;
    }

    public void setText(String text){
        this.text=text;
    }

    public void setToseq(int toseq){
        this.toseq=toseq;
    }

    public void setChatSeq(int chat_seq){
        this.chat_seq=chat_seq;
    }

    public void setFromseq(int fromseq){
        this.fromseq=fromseq;
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