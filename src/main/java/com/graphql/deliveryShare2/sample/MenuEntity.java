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

    public MenuEntity(int seq){
        this.seq=seq;
    }
    public MenuEntity(String name, String thumbnail, String description, int price, Boolean isAvailable, String bestmenu){
        this.name=name;
        this.thumbnail=thumbnail;
        this.description=description;
        this.price=price;
        this.isAvailable = isAvailable;
        this.bestmenu=bestmenu;
    }
    public int getSeq(){
        return seq;
    }
}
