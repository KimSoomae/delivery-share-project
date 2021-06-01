package com.graphql.deliveryShare2.sample.AboutRestaurant;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.graphql.deliveryShare2.sample.AboutResReview.ResReviewEntity;

import javax.persistence.Column;
import java.util.List;
import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import javax.persistence.OneToMany;
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Table(name = "restaurant")
@Getter
public class RestaurantEntity {

    @Id
    @Column(name= "seq", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "id",nullable=false)
    private String id;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "created_at", nullable=false)
    private String created_at;

    @Column(name = "dayoff", nullable=true)
    private String dayoff;

    @Column(name = "isopen", nullable=true)
    private int isopen;

    @Column(name = "min_order", nullable=true)
    private int min_order;

    @Column(name = "delivery_tip", nullable=true)
    private int delivery_tip;

    //@Column(name = "seperatable", nullable=true)
    private Boolean seperatable;

    @Column(name = "introduction", nullable=true)
    private String introduction;

    @Column(name = "thumbnail", nullable=true)
    private String thumbnail;

    @Column(name = "rate", nullable=true)
    private float rate;

    @Column(name = "category", nullable=true)
    private String category;


    @ManyToOne
    @JoinColumn(name="menu_seq", nullable=true)
    private MenuEntity menu;

    @ManyToOne
    @JoinColumn(name="runtime_seq", nullable=true)
    private RunTimeEntity runtime;

    @Column(name = "rate1count", nullable=true)
    private int rate1count;

    @Column(name = "rate2count", nullable=true)
    private int rate2count;

    @Column(name = "rate3count", nullable=true)
    private int rate3count;

    @Column(name = "rate4count", nullable=true)
    private int rate4count;

    @Column(name = "rate5count", nullable=true)
    private int rate5count;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuEntity> bestmenus = new ArrayList<>();
  

    @OneToMany(mappedBy = "restaurant")
    private List<DeliverylocEntity> deliveryloc = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<MenuEntity> menus = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<ResReviewEntity> reviews = new ArrayList<>();
    
    @OneToMany(mappedBy = "restaurant")
    private List<LikesEntity> likes = new ArrayList<>();

    private int reviewcount;

    private int likescount;

    private Boolean isLiked;


    public RestaurantEntity(String id, String password, String name, String created_at, String dayoff, int isopen, int min_order, int delivery_tip, String introduction, String thumbnail, float rate, String category
    ,int rate1count, int rate2count, int rate3count, int rate4count, int rate5count, int reviewcount, int likescount, Boolean isLiked){
        this.name=name;
        this.id=id;
        this.password=password;
        this.created_at=created_at;
        this.dayoff = dayoff;
        this.isopen = isopen;
        this.min_order = min_order;
        this.delivery_tip = delivery_tip;
        this.introduction = introduction;
        this.thumbnail = thumbnail;
        this.rate = rate;
        this.category = category;
        this.rate1count = rate1count;
        this.rate2count = rate2count;
        this.rate3count = rate3count;
        this.rate4count = rate4count;
        this.rate5count = rate5count;
        this.reviewcount=reviewcount;
        this.likescount=likescount;
        this.isLiked=isLiked;
    }

    public List<MenuEntity> getBestmenu(){
        return bestmenus;
    }
    public RunTimeEntity getRunTime(){
        return runtime;
    }
    public List<DeliverylocEntity> getDeliveryloc(){
        return deliveryloc;
    }
    
    public List<MenuEntity> getMenus(){
        return menus;
    }

    public List<ResReviewEntity> getReviews(){
        return reviews;
    }
    public MenuEntity getMenu(){
        return menu;
    }

  
    public void setDayoff(String dayoff){
        this.dayoff=dayoff;
    }
    
    public void setBestmenu(List<MenuEntity> bestmenus){
        this.bestmenus=bestmenus;
    }
    public void setDeliveryloc(List<DeliverylocEntity> deliveryloc){
        this.deliveryloc=deliveryloc;
    }

    public void setMenu(MenuEntity menu){
        this.menu=menu;
    }
    public void setMenus(List<MenuEntity> menus){
        this.menus=menus;
    }
   
    public void setReview(List<ResReviewEntity> reviews){
        this.reviews=reviews;
    }
    public void setRate5count(int rate5count) {
        this.rate5count=rate5count;
      }
    public void setRate4count(int rate4count) {
        this.rate4count=rate4count;
    }
    public void setRate3count(int rate3count) {
        this.rate3count=rate3count;
    }
    public void setRate2count(int rate2count) {
        this.rate2count=rate2count;
    }
    public void setRate1count(int rate1count) {
        this.rate1count=rate1count;
    }

    public double getReviewcount(){
        return reviewcount;
    }
    
    public int getLikescount(){
        return likescount;
    }

    public List<LikesEntity> getLikes(){
        return likes;
    }
    public void setReviewcount(int reviewcount) {
        this.reviewcount=reviewcount;
    }
    
    public void setLikescount(int likescount){
        this.likescount=likescount;
    }
    
    public void setIsliked(Boolean isLiked){
        this.isLiked=isLiked;
    }

    public void setSeperatable(Boolean seperatable){
        this.seperatable=seperatable;
    }

}