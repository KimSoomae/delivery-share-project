package com.graphql.deliveryShare2.sample.AboutCart;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;

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

    @Column(name = "selectedmenu_seq",nullable=true)
    private int selectedmenuSeq;

    @Column(name = "user_seq",nullable=true)
    private int userSeq;

    @ManyToOne
    @JoinColumn(name="selectedmenu_seq", nullable=false, insertable=false, updatable=false)
    private SelectedMenuEntity selectedmenu;

    @ManyToOne
    @JoinColumn(name="user_seq", nullable=false, insertable=false, updatable=false)
    private UserEntity user;


    public CartEntity(String request, SelectedMenuEntity selectedmenu, UserEntity user){
        this.request=request;
        this.selectedmenu=selectedmenu;
        this.user=user;
    }

    public int getSeq(){
        return seq;
    }

    public UserEntity getUser(){
        return user;
    }

    public SelectedMenuEntity getSelectedmenu(){
        return selectedmenu;
    }

    public void setUserSeq(int userSeq){
        this.userSeq=userSeq;
    }

    public void setSelectedmenuSeq(int selectedmenuSeq){
        this.selectedmenuSeq=selectedmenuSeq;
    }
}
