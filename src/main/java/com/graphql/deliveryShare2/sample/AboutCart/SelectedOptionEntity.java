package com.graphql.deliveryShare2.sample.AboutCart;

import java.util.Date;
import java.util.Map;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.graphql.deliveryShare2.sample.AboutRestaurant.OptionItemEntity;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "selected_option")
@Getter
public class SelectedOptionEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name="optionitem_seq",nullable=true)
    private int optionitem_seq;

    @Column(name="selectedmenu_seq",nullable=true)
    private int selectedmenu_seq;

    @ManyToOne
    @JoinColumn(name="optionitem_seq", nullable=true, insertable=false, updatable=false)
    private OptionItemEntity option_item;

    @ManyToOne
    @JoinColumn(name="selectedmenu_seq", nullable=true, insertable=false, updatable=false)
    private SelectedMenuEntity selected_menu;

   
    public SelectedOptionEntity(int seq, int optionitem_seq, int selectedmenu_seq){
        this.seq=seq;
        this.optionitem_seq=optionitem_seq;
        this.selectedmenu_seq = optionitem_seq;
    }

    public SelectedMenuEntity getSelectedMenu(){
        return selected_menu;
    }

    public OptionItemEntity getOptionItem(){
        return option_item;
    }

    public int getOptionItemSeq(){
       return optionitem_seq;
    }

    public void setSelectedmenuSeq(int seq){
        this.selectedmenu_seq = seq;
    }

    public void setOptionItemSeq(int seq){
        this.optionitem_seq = seq;
    }

    

}