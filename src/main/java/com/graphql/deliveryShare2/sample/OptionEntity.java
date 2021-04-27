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
@Table(name = "options")
@Getter
public class OptionEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "category",nullable=false)
    private String category;

    @Column(name = "isRequired", nullable=false)
    private Boolean isRequired;

    @Column(name = "isMultiple", nullable=false)
    private Boolean isMultiple;

    public OptionEntity(String category, Boolean isRequired, Boolean isMultiple){
        this.category=category;
        this.isRequired=isRequired;
        this.isMultiple=isMultiple;
    }
}
