package com.graphql.deliveryShare2.sample;

import java.util.Date;
import java.util.Map;
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

   
    public SelectedMenuEntity(int count, int menuSeq){
        this.count=count;
        this.menuSeq=menuSeq;
    }

    public int getMenuSeq(){
        return menuSeq;
    }

    public MenuEntity getMenu(){
        return menu;
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

    

}
