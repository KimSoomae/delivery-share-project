package com.graphql.deliveryShare2.sample.AboutCall;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantEntity;
import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;

import org.springframework.scheduling.annotation.Scheduled;

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


    @Column(name = "created_at", nullable=false)
    private OffsetDateTime created_at;


    @Column(name = "expired_at", nullable=false)
    private OffsetDateTime expired_at;

    //@Column(name = "request_R", nullable=true)
    //private String request;

    @Column(name = "request_call", nullable=false)
    private String request_call;

    @Column(name = "time_limit", nullable=false)
    private int time_limit;

    @Column(name = "status", nullable=false)
    private String status;

    @Column(name = "calltext", nullable=true)
    private String calltext;

    @Column(name = "price", nullable=false)
    private int price;

    @Column(name="user_seq_fk_idx",nullable=true)
    private Long user_seq;

    @Column(name="restaurant_seq_fk_idx",nullable=true)
    private int res_seq;

    @Column(name="callLocation_seq_fk_idx",nullable=true)
    private int location_seq;

    @ManyToOne
    @JoinColumn(name="user_seq_fk_idx", nullable=false, insertable=false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="restaurant_seq_fk_idx", nullable=false, insertable=false, updatable = false)
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name="callLocation_seq_fk_idx", nullable=false, insertable=false, updatable = false)
    private CallLocationEntity callLocation;

    //@Column(name = "cart_seq", nullable=false)
    //private int cart1;
    //@ManyToOne
    //@JoinColumn(name="cart_seq", nullable=false)
    //private CartEntity cart1;

    @OneToMany(mappedBy = "call")
    private List<CartEntity> cart= new ArrayList<>();

    


    private Double distance;

    
//CartEntity cart1
    public CallingEntity(List<CartEntity> cart){
        this.cart=cart;
    }
    public CallingEntity(OffsetDateTime created_at, OffsetDateTime expired_at, String status, String calltext, int price, UserEntity user, RestaurantEntity restaurant, CallLocationEntity callLocation, String request_call, int time_limit, Long user_seq ){
        
        this.created_at=created_at;
        this.expired_at=expired_at;
        this.status=status;
        this.calltext=calltext;
        this.price = price;
        this.user=user;
        this.restaurant=restaurant;
        this.callLocation = callLocation;
        //this.cart1 = cart1;
        //this.request = request;
        this.request_call = request_call;
        this.time_limit = time_limit;
        this.user_seq=user_seq;
    }

    public int getSeq(){
        return seq;
    }

    public int getResSeq(){
        return res_seq;
    }
    public String getStatus(){
        return status;
    }

    public Long getUserSeq(){
        return user_seq;
    }

    public UserEntity getUser(){
        return user;
    }

    public OffsetDateTime getExpired_at(){
        return expired_at;
    }
    public double getDistance(){
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance=distance;
      }
    

    public RestaurantEntity getRestaurant(){
        return restaurant;
    }

    public CallLocationEntity getCallLocation(){
        return callLocation;
    }

   // public CartEntity getCart1(){
    //    return cart1;
    //}

    public List<CartEntity> getCart(){
        return cart;
    }

    public List<CallingEntity> getNearCalls(Double latitude, Double longitude){
        return this.getNearCalls(latitude, longitude);
    }

    public void setCreatedAt(){
        LocalDateTime dateTime = LocalDateTime.now();
        ZoneOffset offset = ZoneOffset.UTC;
        this.created_at = dateTime.atOffset(offset);
    }

    public void setExpiredAt(int time){
        LocalDateTime dateTime = LocalDateTime.now();
        ZoneOffset offset = ZoneOffset.UTC;
        this.expired_at = dateTime.atOffset(offset).plusMinutes(time);
    }

    public void setStatus(String status){
        this.status=status;
    }

    
    public void setPrice(int price){
        this.price=price;
    }
    public void setRequestCall(String request){
        this.request_call=request;
    }

    public void setTimelimit(int timelimit){
        this.time_limit=timelimit;
    }

    public void setUserseq(Long userseq){
        this.user_seq = userseq;
    }

    public void setResseq(int resseq){
        this.res_seq=resseq;
    }

    public void setLocationseq(int locationseq){
        this.location_seq=locationseq;
    }
    
 
}

