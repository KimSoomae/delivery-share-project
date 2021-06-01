package com.graphql.deliveryShare2.sample.AboutRestaurant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "menu")
@Getter
public class MenuEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "thumbnail", nullable=true)
    private String thumbnail;

    @Column(name = "description", nullable=true)
    private String description;

    @Column(name = "price", nullable=false)
    private int price;

    @Column(name = "isAvailable", nullable=false)
    private Boolean isAvailable;

    @Column(name = "bestmenu", nullable=true)
    private String bestmenu;

    @Column(name = "resseq", nullable=false)
    private int resseq;

    @ManyToOne
    @JoinColumn(name="resseq", nullable=true, insertable=false, updatable=false)
    private RestaurantEntity restaurant;

    public MenuEntity(int seq){
        this.seq=seq;
    }
    public MenuEntity(String name, String thumbnail, String description, int price, Boolean isAvailable, String bestmenu, int resseq){
        this.name=name;
        this.thumbnail=thumbnail;
        this.description=description;
        this.price=price;
        this.isAvailable = isAvailable;
        this.bestmenu=bestmenu;
        this.resseq=resseq;
    }
    public int getSeq(){
        return seq;
    }

    public int getPrice(){
        return price;
    }
    public RestaurantEntity getRestaurant(){
        return restaurant;
    }
}