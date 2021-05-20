package com.graphql.deliveryShare2.sample.AboutUser;

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
@Table(name = "report")
@Getter
public class ReportEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "reason",nullable=false)
    private String reason;

    @Column(name = "isSolved", nullable=false)
    private Boolean isSolved;

    //@Column(name = "createdAt", nullable=false)
    //private String createdAt;

    //@Column(name = "updatedAt", nullable=false)
    //private String updatedAt;


    public ReportEntity(String reason, Boolean isSolved){
        this.reason=reason;
        this.isSolved=isSolved;
    }
}
