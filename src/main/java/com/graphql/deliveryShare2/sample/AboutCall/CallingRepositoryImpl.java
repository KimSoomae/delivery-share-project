package com.graphql.deliveryShare2.sample.AboutCall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
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
public class CallingRepositoryImpl implements CallingCustomRepository{
   
    final String PERSISTENCE_UNIT_NAME = "jpa";
    @PersistenceUnit
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    
    @Override
    public List<CallingEntity> getNearCalls(){
        
        EntityManager em = emf.createEntityManager();
        
        //현재 위치 한국외대 경영대로 가정
        List<CallingEntity> results = em.createQuery("SELECT c"
    +" FROM CallingEntity AS c"
    +" LEFT OUTER JOIN CallLocationEntity AS l"
    +" ON l.seq=c.callLocation"
    +" WHERE (((acos(sin((37.59654772753874*pi()/180)) * sin((l.latitude*pi()/180)) + cos((37.59654772753874*pi()/180)) * cos((l.latitude*pi()/180)) * cos(((127.05991033848542- l.longitude) * pi()/180)))) * 180/pi()) * 60 * 1.1515 * 1.609344) <= 0.5"
    ,CallingEntity.class).getResultList();

    for (int i=0; i<results.size();i++){
        CallingEntity ce= results.get(i);
        CallLocationEntity cl = ce.getCallLocation();
        double lat=cl.getLatitude();
        double lon=cl.getLongitude();
        double pi=3.141592653589793;
        double distance = (((Math.acos(Math.sin((37.59654772753874*pi/180)) * 
        Math.sin((lat*pi/180)) + Math.cos((37.59654772753874*pi/180)) * 
        Math.cos((lat*pi/180)) * Math.cos(((127.05991033848542- lon) * pi/180)))) 
        * 180/pi * 60 * 1.1515 * 1.609344));
        ce.setDistance(distance*1000);
        
    }

        System.out.println(results);
        return results;
   
    };
}