package com.graphql.deliveryShare2.sample.AboutCart;
import java.util.List;
import java.util.ArrayList;

import java.util.Date;
import java.util.Map;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.graphql.deliveryShare2.sample.AboutRestaurant.MenuEntity;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "selected_menu")
@Getter
public class SelectedMenuEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "count",nullable=false)
    private int count;

    @Column(name="menu_seq",nullable=true)
    private int menuSeq;

    @ManyToOne
    @JoinColumn(name="menu_seq", nullable=true, insertable=false, updatable=false)
    private MenuEntity menu;

    @Column(name="cart_seq",nullable=true)
    private int cartSeq;

    @Column(name="price", nullable=true)
    private int price;

    @Column(name="isSeperated", nullable=true)
    private Boolean isSeperated;

    @ManyToOne
    @JoinColumn(name="cart_seq", nullable=true, insertable=false, updatable=false)
    private CartEntity cart;

    @OneToMany(mappedBy = "selected_menu")
    private List<SelectedOptionEntity> selected_option = new ArrayList<>();


    public SelectedMenuEntity(int count, int menuSeq, CartEntity cart, int price, Boolean isSeperated){
        this.count=count;
        this.menuSeq=menuSeq;
        this.cart = cart;
        this.price=price;
        this.isSeperated=isSeperated;
    }

    public int getMenuSeq(){
        return menuSeq;
    }

    public int getCount(){
        return count;
    }
    public MenuEntity getMenu(){
        return menu;
    }

    public CartEntity getCart(){
        return cart;
    }


    public int getSeq(){
        return seq;
    }

    public void setMenuSeq(int menuSeq){
        this.menuSeq=menuSeq;
    }
    //public void setMenu(MenuEntity menu){
    //    this.menu=menu;
   // }

    public void setCount(int count){
        this.count=count;
    }

    public void setCartseq(int cartseq){
        this.cartSeq=cartseq;
    }

    public List<SelectedOptionEntity> getSelectedOption(){
        return selected_option;
    }

    public void setPrice(int price){
        this.price=price;
    }

    public void setIsSeperated(Boolean isSeperated){
        this.isSeperated=isSeperated;
    }
    

}
