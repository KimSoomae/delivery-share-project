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
@Table(name = "USER")
@Getter
public class UserEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "ID",nullable=false)
    private String ID;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "name", nullable=false)
    private String name;

    //@Column(name = "createdAt", nullable=false)
    //private String createdAt;

    //@Column(name = "updatedAt", nullable=true)
    //private String updatedAt;

    @Column(name = "thumbnail", nullable=true)
    private String thumbnail;

    @Column(name = "token", nullable=true)
    private String token;

    @Column(name = "status", nullable=false)
    private String status;

    public UserEntity(String ID, String name, String password, String status){
        this.ID=ID;
        this.name=name;
        this.password=password;
        this.status=status;
    }

}
