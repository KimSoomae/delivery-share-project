package com.graphql.deliveryShare2.sample.AboutUser;

import com.graphql.deliveryShare2.sample.AboutChat.ChatEntity;
import com.graphql.deliveryShare2.sample.AboutCart.CartEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "user")
@Getter
public class UserEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "ID",nullable=false)
    private String ID;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "name", nullable=false)
    private String name;

    //@Column(name = "createdAt", nullable=false)
    //private String createdAt;

    @Column(name = "created_at", nullable=false)
    private String created_at;

    //@Column(name = "updatedAt", nullable=true)
    //private String updatedAt;
    @Column(name = "updated_at", nullable=true)
    private String updated_at;

    @Column(name = "thumbnail", nullable=true)
    private String thumbnail;

    @Column(name = "token", nullable=true)
    private String token;

    @Column(name = "status", nullable=false)
    private String status;

    @Column(name="rate", nullable=false)
    private Double rate;

    @Column(name = "can_write_review",nullable=true)
    private boolean canWriteReview;

    @OneToMany(mappedBy = "to")
    private List<UserReviewEntity> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user") 
    private List<CartEntity> carts = new ArrayList<>();

    private int orderCounts;

   
    public UserEntity(Long seq, String ID, String name, String password, String created_at, String updated_at, String status, Double rate, int orderCounts, boolean canWriteReview){
        this.seq=seq;
        this.ID=ID;
        this.name=name;
        this.password=password;
        //this.createdAt=createdAt;
        //this.updatedAt=updatedAt;
        this.created_at=created_at;
        this.updated_at=updated_at;
        this.status=status;
        this.rate=rate;
        this.orderCounts = orderCounts;
        this.canWriteReview = canWriteReview;
    }
    public Long getSeq(){
        return seq;
    }

    public Boolean getCanWriteReview(){
        return canWriteReview;
    }

    public void setOrderCounts(int orderCounts){
        this.orderCounts=orderCounts;
    }

    public void setReviews(List<UserReviewEntity> reviews){
        this.reviews=reviews;
    }

    public void setRate(double rate){
        this.rate=rate;
    }
    
    public List<CartEntity> getCart(){
        return carts;
    }


    public void setCanWriteReview(boolean bool){
        if(bool == true){
            this.canWriteReview = true;
        }
        else{
            this.canWriteReview = false;
        }
    }
    
}