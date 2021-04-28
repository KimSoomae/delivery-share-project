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
@Table(name = "calling")
@Getter
public class CallingEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;



    @Column(name = "createdAt", nullable=false)
    private String createdAt;

    @Column(name = "expiredAt", nullable=false)
    private String expiredAt;

    @Column(name = "status", nullable=false)
    private String status;

    @Column(name = "calltext", nullable=true)
    private String calltext;

    @Column(name = "price", nullable=false)
    private int price;

    public CallingEntity(String createdAt, String expiredAt, String status, String calltext, int price){
        this.createdAt=createdAt;
        this.expiredAt=expiredAt;
        this.status=status;
        this.calltext=calltext;
        this.price = price;
    }

}

