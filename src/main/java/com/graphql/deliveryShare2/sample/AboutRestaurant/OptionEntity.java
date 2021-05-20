package com.graphql.deliveryShare2.sample.AboutRestaurant;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

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

    @Column(name = "is_required", nullable=false)
    private Boolean is_required;

    @Column(name = "is_multiple", nullable=false)
    private Boolean is_multiple;

    @Column(name = "menuseq", nullable=false)
    private int menuseq;

    

    //@ManyToOne
    //@JoinColumn(name="seq", nullable=true, insertable=false, updatable=false)
    //private OptionItemEntity option_item ;
    @OneToMany(mappedBy = "option")
    //private OptionItemEntity option_item;
    private List<OptionItemEntity> option_item = new ArrayList<>();

    

    public OptionEntity(String category, Boolean is_required, Boolean is_multiple, int menuseq){
        this.category=category;
        this.is_required=is_required;
        this.is_multiple=is_multiple;
        this.menuseq = menuseq;
    }

    public List<OptionItemEntity> getOptionItem(){
        return option_item;
    }
}