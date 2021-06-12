package com.graphql.deliveryShare2.sample.AboutResReview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import com.graphql.deliveryShare2.sample.AboutCall.GeometryUtil;
import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantEntity;
import com.graphql.deliveryShare2.sample.AboutRestaurant.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.Geometry;
import org.springframework.stereotype.Service;
import org.locationtech.jts.geom.Point;
import lombok.RequiredArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.Id;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
@Repository
@Transactional
public class ResReviewRepositoryImpl implements ResReviewCustomRepository{
   
    final String PERSISTENCE_UNIT_NAME = "resreview";
    @PersistenceUnit
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    
    @Override
    public RestaurantEntity getReviewCount(@Param("resseq") int resseq){
        RestaurantEntity re= new RestaurantEntity();
        
        EntityManager em = emf.createEntityManager();
        
        
        List<ResReviewEntity> results = em.createQuery("SELECT v"
    +" FROM ResReviewEntity AS v"
    +" LEFT OUTER JOIN RestaurantEntity AS r"
    +" ON r.seq=v.restaurant"
    +" WHERE v.resseq = :resseq "
    ,ResReviewEntity.class).setParameter("resseq", resseq).getResultList();

    for (int i=0; i<results.size();i++){
        ResReviewEntity rre= results.get(i);
        re = rre.getRestaurant();
        
        double rate=rre.getRate();
        if (rate==5.0){
            int cnt = re.getRate5count();
            cnt++;
            re.setRate5count(cnt);
        }
        else if ((rate<5.0)&&(rate>=4.0)){
            int cnt = re.getRate4count();
            cnt++;
            re.setRate4count(cnt);
        }
        else if ((rate<4.0)&&(rate>=3.0)){
            int cnt = re.getRate3count();
            cnt++;
            re.setRate3count(cnt);
        }
        else if ((rate<3.0)&&(rate>=2.0)){
            int cnt = re.getRate2count();
            cnt++;
            re.setRate2count(cnt);
        }
        else if ((rate<2.0)&&(rate>=1.0)){
            int cnt = re.getRate1count();
            cnt++;
            re.setRate1count(cnt);
        }
       
        
    }

    return re;
   
    };
}