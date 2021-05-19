package com.graphql.deliveryShare2.sample;

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

    @Column(name = "isopen", nullable=true)
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

    @ManyToOne
    @JoinColumn(name="runtime_seq", nullable=true)
    private RunTimeEntity runtime;

    @Column(name = "rate1count", nullable=true)
    private int rate1count;

    @Column(name = "rate2count", nullable=true)
    private int rate2count;

    @Column(name = "rate3count", nullable=true)
    private int rate3count;

    @Column(name = "rate4count", nullable=true)
    private int rate4count;

    @Column(name = "rate5count", nullable=true)
    private int rate5count;
    
    public RestaurantEntity(String id, String password, String name, String created_at, String dayoff, int is_open, int min_order, int delivery_tip, int seperatable, String introduction, String thumbnail, float rate, String category
    ,int rate1count, int rate2count, int rate3count, int rate4count, int rate5count){
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
        this.rate1count = rate1count;
        this.rate2count = rate2count;
        this.rate3count = rate3count;
        this.rate4count = rate4count;
        this.rate5count = rate5count;
    }

    public RunTimeEntity getRunTime(){
        return runtime;
    }

    public void setRate5count(int rate5count) {
        this.rate5count=rate5count;
      }
    public void setRate4count(int rate4count) {
        this.rate4count=rate4count;
    }
    public void setRate3count(int rate3count) {
        this.rate3count=rate3count;
    }
    public void setRate2count(int rate2count) {
        this.rate2count=rate2count;
    }
    public void setRate1count(int rate1count) {
        this.rate1count=rate1count;
    }

   

}
