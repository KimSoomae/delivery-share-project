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
@Table(name = "selected_menu")
@Getter
public class SelectedMenuEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "count",nullable=false)
    private int count;

   

    public SelectedMenuEntity(int count){
        this.count=count;
    }

}
