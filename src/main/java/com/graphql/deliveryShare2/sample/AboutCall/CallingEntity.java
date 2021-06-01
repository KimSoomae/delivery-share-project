package com.graphql.deliveryShare2.sample.AboutCall;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.ArrayList;

import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantEntity;
import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;
import com.fasterxml.jackson.module.kotlin.ReflectionCache.BooleanTriState.True;
import com.graphql.deliveryShare2.sample.AboutCart.CartEntity;

import javax.persistence.Column;
import java.util.List;
import java.util.Date;
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
    @Column(name = "created_at", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    private Date created_at;

    @Column(name = "expiredAt", nullable=false)
    private String expiredAt;
    @Column(name = "expired_at", nullable=false)
    private String expired_at;

    @Column(name = "status", nullable=false)
    private String status;

    @Column(name = "calltext", nullable=true)
    private String calltext;

    @Column(name = "price", nullable=false)
    private int price;

    @ManyToOne
    @JoinColumn(name="user_seq_fk_idx", nullable=false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="restaurant_seq_fk_idx", nullable=false)
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name="callLocation_seq_fk_idx", nullable=false)
    private CallLocationEntity callLocation;

    @ManyToOne
    @JoinColumn(name="cart_seq", nullable=false)
    private CartEntity cart1;

    @OneToMany(mappedBy = "call")
    private List<CartEntity> cart = new ArrayList<>();

    


    private Double distance;

    public CallingEntity(Date createdAt, String expiredAt, String status, String calltext, int price, UserEntity user, RestaurantEntity restaurant, CallLocationEntity callLocation){
        this.createdAt=createdAt;
        this.expiredAt=expiredAt;
    public CallingEntity(Date created_at, String expired_at, String status, String calltext, int price, UserEntity user, RestaurantEntity restaurant, CallLocationEntity callLocation, CartEntity cart1){
        
        this.created_at=created_at;
        this.expired_at=expired_at;
        this.status=status;
        this.calltext=calltext;
        this.price = price;
        this.user=user;
        this.restaurant=restaurant;
        this.callLocation = callLocation;
        this.cart1 = cart1;
      
    }

    public int getSeq(){
        return seq;
    }

    public UserEntity getUser(){
        return user;
    }

    public double getDistance(){
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance=distance;
      }
    }

    public RestaurantEntity getRestaurant(){
        return restaurant;
    }

    public CallLocationEntity getCallLocation(){
        return callLocation;
    }

    public CartEntity getCart1(){
        return cart1;
    }

    public List<CartEntity> getCart(){
        return cart;
    }


    public List<CallingEntity> getNearCalls(){
        return this.getNearCalls();
    public List<CallingEntity> getNearCalls(Double latitude, Double longitude){
        return this.getNearCalls(latitude, longitude);
    }
 
}

