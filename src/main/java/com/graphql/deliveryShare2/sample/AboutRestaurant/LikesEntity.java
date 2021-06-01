package com.graphql.deliveryShare2.sample.AboutRestaurant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;
import java.io.Serializable;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "likes")
@Getter
public class LikesEntity implements Serializable {
    
    @Id
    @Column(name = "seq",nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "res_seq", nullable=false)
    private int resseq;

    @ManyToOne
    @JoinColumn(name="res_seq", nullable=true, insertable=false, updatable=false)
    private RestaurantEntity restaurant;
    
    @Column(name = "user_seq", nullable=false)
    private int userseq;

    @ManyToOne
    @JoinColumn(name="user_seq", nullable=true, insertable=false, updatable=false)
    private UserEntity user;

    public void setResseq(int resseq){
        this.resseq=resseq;
    }
    
    public void setUserseq(int userseq){
        this.userseq=userseq;
    }
   

}
