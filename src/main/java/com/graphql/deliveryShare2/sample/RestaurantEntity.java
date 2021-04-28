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
@Table(name = "restaurant")
@Getter
public class RestaurantEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "id",nullable=false)
    private String id;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "created_at", nullable=false)
    private String created_at;

    @Column(name = "dayoff", nullable=true)
    private String dayoff;

    @Column(name = "is_open", nullable=true)
    private int is_open;

    @Column(name = "min_order", nullable=true)
    private int min_order;

    @Column(name = "delivery_tip", nullable=true)
    private int delivery_tip;

    @Column(name = "seperatable", nullable=true)
    private int seperatable;

    @Column(name = "introduction", nullable=true)
    private String introduction;

    @Column(name = "thumbnail", nullable=true)
    private String thumbnail;

    @Column(name = "rate", nullable=true)
    private float rate;

    @Column(name = "category", nullable=true)
    private String category;


   

    public RestaurantEntity(String id, String password, String name, String created_at, String dayoff, int is_open, int min_order, int delivery_tip, int seperatable, String introduction, String thumbnail, float rate, String category){
        this.name=name;
        this.id=id;
        this.password=password;
        this.created_at=created_at;
        this.dayoff = dayoff;
        this.is_open = is_open;
        this.min_order = min_order;
        this.delivery_tip = delivery_tip;
        this.seperatable = seperatable;
        this.introduction = introduction;
        this.thumbnail = thumbnail;
        this.rate = rate;
        this.category = category;
    }

}
