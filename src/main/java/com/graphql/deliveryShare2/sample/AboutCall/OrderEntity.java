package com.graphql.deliveryShare2.sample.AboutCall;
import javax.persistence.*;

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
@Table(name = "orders")
@Getter
public class OrderEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    //@Column(name = "createdAt", nullable=false)
    //private String createdAt;
    @Column(name = "created_at", nullable=false)
    private String created_at;

    //@Column(name = "updatedAt", nullable=false)
    //private String updatedAt;

    //@Column(name = "deliveryTime", nullable=false)
    //private String deliveryTime;
    @Column(name = "delivery_time", nullable=false)
    private int delivery_time;

    @Column(name = "status",nullable=false)
    private String status;

    @Column(name = "canWriteRestaurantReview",nullable=false)
    private boolean canWriteRestaurantReview;

    @Column(name = "canWriteUserReview",nullable=false)
    private boolean canWriteUserReview;
    

    @ManyToOne
    @JoinColumn(name="call_seq", nullable=true, insertable=false, updatable=false)
    private CallingEntity call;

    public OrderEntity(String status){
        this.status=status;
    }

    public CallingEntity getCall() {
        return call;
    }
}
