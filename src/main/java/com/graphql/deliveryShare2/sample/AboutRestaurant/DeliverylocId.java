package com.graphql.deliveryShare2.sample.AboutRestaurant;

import java.io.Serializable;

public class DeliverylocId implements Serializable {

    private int resseq;
    private String si;
    private String dong;

    public DeliverylocId(){

    }
    public int getResseq(){
        return this.resseq;
    }

    public String getSi(){
        return this.si;

    }

    public String getDong(){
        return this.dong;
    }

    public void setResseq(int resseq){
        this.resseq = resseq;
    }

    public void setSi(String si){
        this.si = si;
    }

    public void setDong(String dong){
        this.dong= dong;
    }



}
