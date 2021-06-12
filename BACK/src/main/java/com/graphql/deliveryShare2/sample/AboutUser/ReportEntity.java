package com.graphql.deliveryShare2.sample.AboutUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

import com.graphql.deliveryShare2.sample.AboutChat.ChatEntity;
import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "report")
@Getter
public class ReportEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "reason",nullable=false)
    private String reason;

    @Column(name = "content",nullable=false)
    private String content;

    @Column(name = "isSolved", nullable=false)
    private Boolean isSolved;
    
    @Column(name = "created_at", nullable=false)
    private OffsetDateTime created_at;

    @Column(name = "updated_at", nullable=true)
    private OffsetDateTime updated_at;

    @Column(name = "reporter_seq", nullable=false)
    private Long reporter_seq;

    @Column(name = "reported_seq", nullable=false)
    private Long reported_seq;

    @Column(name = "chatseq", nullable=false)
    private int chatseq;

    @OneToOne
    @JoinColumn(name="chatseq", nullable=true, insertable=false, updatable=false)
    private ChatEntity chat;
    
    @ManyToOne
    @JoinColumn(name="reporter_seq", nullable=true,insertable=false, updatable=false)
    private UserEntity reporter;

    @ManyToOne
    @JoinColumn(name="reported_seq", nullable=true,insertable=false, updatable=false)
    private UserEntity reported;
    
    public ReportEntity(String reason, String content, Boolean isSolved, OffsetDateTime created_at, OffsetDateTime updated_at, int chatseq, UserEntity reporter, UserEntity reported){
        this.reason=reason;
        this.content = content;
        this.isSolved=isSolved;
        this.created_at=created_at;
        this.updated_at=updated_at;
        this.chatseq = chatseq;
        this.reporter = reporter;
        this.reported = reported;
    }

    public void setReason(String reason){
        this.reason=reason;
    }

    public void setContent(String content){
        this.content=content;
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

    public void setChatSeq(int chatseq){
        this.chatseq=chatseq;
    }


    public int getChatseq(){
        return chatseq;
    }
    
    public int getSeq () {
        return seq;
      }

    public void setReporter_seq(Long reporter_seq){
        this.reporter_seq = reporter_seq;
    }

    public void setReported_seq(Long reported_seq){
        this.reported_seq = reported_seq;
    }

    public void setIsSolved(Boolean isSolved){
        this.isSolved = isSolved;
    }

}
