package com.graphql.deliveryShare2.sample.AboutCall;
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
@Table(name = "call_location")
@Getter
public class CallLocationEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "place", nullable=true)
    private String place;

    @Column(name = "latitude", nullable=false)
    private double latitude;

    @Column(name = "longitude", nullable=false)
    private double longitude;

    public CallLocationEntity(String place,Double latitude, Double longitude){
        this.place=place;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public int getSeq(){
        return seq;
    }

    public void setPlace(String place){
            this.place = place;
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }


}

