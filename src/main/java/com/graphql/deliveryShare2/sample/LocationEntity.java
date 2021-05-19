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
@Table(name = "location")
@Getter
public class LocationEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "latitude",nullable=false)
    private float latitude;

    @Column(name = "longitude", nullable=false)
    private float longitude;

    @Column(name = "address", nullable=false)
    private String address;

    public LocationEntity(float latitude, float longitude, String address){
        this.latitude=latitude;
        this.longitude=longitude;
        this.address=address;
    }
}
