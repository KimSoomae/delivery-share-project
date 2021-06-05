package com.graphql.deliveryShare2.sample.AboutUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

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
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable=false)
    private OffsetDateTime updatedAt;

    public ReportEntity(String reason, Boolean isSolved){
        this.reason=reason;
        this.isSolved=isSolved;
    }

    public void setContent(String content){
        this.content=content;
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
