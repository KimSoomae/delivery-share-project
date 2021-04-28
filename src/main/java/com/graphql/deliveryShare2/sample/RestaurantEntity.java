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

    @Column(name = "createdAt", nullable=false)
    private String createdAt;

    @Column(name = "dayoff", nullable=true)
    private String dayoff;

    @Column(name = "isOpen", nullable=true)

    @Column(name = "minOrder", nullable=true)
    private int minOrder;

    @Column(name = "deliveryTip", nullable=true)
    private int deliveryTip;

    @Column(name = "seperatable", nullable=true)
    private int seperatable;

    @Column(name = "introduction", nullable=true)
    private String introduction;

    @Column(name = "thumbnail", nullable=true)
    private String thumbnail;

    @Column(name = "rate", nullable=true)
    private float rate;

    @Column(name = "category", nullable=true)

        this.name=name;
        this.id=id;
        this.password=password;
        this.createdAt=createdAt;
        this.dayoff = dayoff;
        this.isOpen = isOpen;
        this.minOrder = minOrder;
        this.deliveryTip = deliveryTip;
        this.seperatable = seperatable;
        this.introduction = introduction;
        this.thumbnail = thumbnail;
        this.rate = rate;
        this.category = category;
    }

}
