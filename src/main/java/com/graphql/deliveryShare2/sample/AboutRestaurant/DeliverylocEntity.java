package com.graphql.deliveryShare2.sample.AboutRestaurant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import java.io.Serializable;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "delivery_loc")
@Getter
public class DeliverylocEntity implements Serializable {
    
    @Id
    @Column(name = "res_seq",nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resseq;

    @Id
    @Column(name = "si",nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String si;

    @Id
    @Column(name = "dong",nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String dong;
    

    @ManyToOne
    @JoinColumn(name="res_seq", nullable=true, insertable=false, updatable=false)
    private RestaurantEntity restaurant;

}
