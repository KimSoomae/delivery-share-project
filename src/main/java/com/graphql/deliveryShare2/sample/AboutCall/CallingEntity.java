package com.graphql.deliveryShare2.sample.AboutCall;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantEntity;
import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;

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
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "expiredAt", nullable=false)
    private String expiredAt;

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


    private Double distance;

    public CallingEntity(Date createdAt, String expiredAt, String status, String calltext, int price, UserEntity user, RestaurantEntity restaurant, CallLocationEntity callLocation){
        this.createdAt=createdAt;
        this.expiredAt=expiredAt;
        this.status=status;
        this.calltext=calltext;
        this.price = price;
        this.user=user;
        this.restaurant=restaurant;
        this.callLocation = callLocation;
      
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
    public RestaurantEntity getRestaurant(){
        return restaurant;
    }

    public CallLocationEntity getCallLocation(){
        return callLocation;
    }


    public List<CallingEntity> getNearCalls(){
        return this.getNearCalls();
    }
 
}

