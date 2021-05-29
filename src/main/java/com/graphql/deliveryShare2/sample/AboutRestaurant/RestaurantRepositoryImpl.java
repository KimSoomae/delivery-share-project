package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import com.graphql.deliveryShare2.sample.AboutRestaurant.DeliverylocEntity;
import com.graphql.deliveryShare2.sample.AboutCall.GeometryUtil;
import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantEntity;
import com.graphql.deliveryShare2.sample.AboutRestaurant.*;
import com.graphql.deliveryShare2.sample.AboutResReview.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.Geometry;
import org.springframework.stereotype.Service;
import java.util.List;
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
public class RestaurantRepositoryImpl implements RestaurantCustomRepository{
   
    final String PERSISTENCE_UNIT_NAME = "restaurant";
    @PersistenceUnit
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    
    @Override
    public List<RestaurantEntity> getPossibleRestaurants(@Param("category") String category, @Param("si") String si, @Param("dong") String dong){
        //RestaurantEntity re= new RestaurantEntity();
        
        EntityManager em = emf.createEntityManager();
        
        List<RestaurantEntity> results = em.createQuery("SELECT r"
    +" FROM RestaurantEntity AS r"
    +" INNER JOIN DeliverylocEntity AS d"
    +" ON d.resseq=r.seq"
    +" WHERE r.category = :category "
    +" AND :si =d.si AND :dong = d.dong "
    //si,dong에 해당하는 것들
    ,RestaurantEntity.class).setParameter("category", category).setParameter("si",si).setParameter("dong",dong).getResultList();

    for (int i=0; i<results.size();i++){
        RestaurantEntity re= results.get(i);
        EntityManager em2 = emf.createEntityManager();
        List<MenuEntity> menus = em2.createQuery("SELECT m"
        +" FROM MenuEntity AS m"
        +" WHERE m.resseq = :seq "
        +" AND m.bestmenu = 1 "
        ,MenuEntity.class).setParameter("seq", re.getSeq()).getResultList();
        System.out.println("메뉴들!!");
        System.out.println(menus);
        EntityManager em3 = emf.createEntityManager();
        List<ResReviewEntity> reviews = em3.createQuery("SELECT r"
        +" FROM ResReviewEntity As r"
        +" WHERE r.resseq= :seq "
        ,ResReviewEntity.class).setParameter("seq", re.getSeq()).getResultList();
        int reviewcnt = reviews.size();
        System.out.println("리뷰총개수!!"+reviewcnt);
        re.setReviewcount(reviewcnt);
        re.setBestmenu(menus);
        
    }
    return results;
   
    };
}