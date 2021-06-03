package com.graphql.deliveryShare2.sample.AboutCart;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;
import com.graphql.deliveryShare2.sample.AboutCall.CallingEntity;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "cart")
@Getter
public class CartEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "request",nullable=true)
    private String request;

    //@Column(name = "selectedmenu_seq",nullable=true)
    //private int selectedmenuSeq;

    @Column(name = "delivery_tip",nullable=true)
    private int delivery_tip;

    @Column(name = "user_seq",nullable=true)
    private int userSeq;

    
    //@ManyToOne
    //@JoinColumn(name="selectedmenu_seq", nullable=false, insertable=false, updatable=false)
    //private SelectedMenuEntity selectedmenu;

    @ManyToOne
    @JoinColumn(name="user_seq", nullable=false, insertable=false, updatable=false)
    private UserEntity user;


    @Column(name = "call_seq",nullable=true)
    private int call_seq;

    private int total_cost;

    @ManyToOne
    @JoinColumn(name="call_seq", nullable=false, insertable=false, updatable=false)
    private CallingEntity call;

    @OneToMany(mappedBy = "cart") 
    //private OptionItemEntity option_item;
    private List<SelectedMenuEntity> selected_menu = new ArrayList<>();


    public CartEntity(String request, UserEntity user, int delivery_tip, CallingEntity call, int call_seq, int userSeq){
        this.request=request;
        //this.selectedmenuSeq=selectedmenuSeq;
        //this.selectedmenu=selectedmenu;
        this.user=user;
        this.delivery_tip = delivery_tip;
        this.call=call; 
        this.call_seq=call_seq;
        this.userSeq =userSeq;

    }

    public int getSeq(){
        return seq;
    }

    public UserEntity getUser(){
        return user;
    }

    //public CallingEntity getCall(){
    //    return call;
    //}

    //public SelectedMenuEntity getSelectedmenu(){
     //   return selectedmenu;
    //}

    public void setUserSeq(int userSeq){
        this.userSeq=userSeq;
    }

    public void setTotalCost(int total_cost){
        this.total_cost+=total_cost;
    }
    //public void setSelectedmenuSeq(int selectedmenuSeq){
    //    this.selectedmenuSeq=selectedmenuSeq;
    //}

    public List<SelectedMenuEntity> getSelectedMenu(){
        return selected_menu;
    }
}