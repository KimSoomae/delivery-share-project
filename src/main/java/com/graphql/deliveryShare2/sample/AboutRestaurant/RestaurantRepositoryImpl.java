package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import com.graphql.deliveryShare2.sample.AboutRestaurant.DeliverylocEntity;
import com.graphql.deliveryShare2.sample.AboutCall.GeometryUtil;
import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantEntity;
import com.graphql.deliveryShare2.sample.AboutRestaurant.*;
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
        RestaurantEntity re= new RestaurantEntity();
        
        EntityManager em = emf.createEntityManager();
        
        List<RestaurantEntity> results = em.createQuery("SELECT r"
    +" FROM RestaurantEntity AS r"
    +" INNER JOIN DeliverylocEntity AS d"
    +" ON d.resseq=r.seq"
    +" WHERE r.category = :category "
    +" AND :si =d.si AND :dong = d.dong "
    //si,dong에 해당하는 것들
    ,RestaurantEntity.class).setParameter("category", category).setParameter("si",si).setParameter("dong",dong).getResultList();

   
    return results;
   
    };
}