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
@Table(name = "run_time")
@Getter
public class RunTimeEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "day",nullable=false)
    private String day;

    @Column(name = "open", nullable=false)
    private String open;

    @Column(name = "close", nullable=false)
    private String close;


    public RunTimeEntity(String day, String open, String close){
        this.day=day;
        this.open=open;
        this.close=close;

    }

}
