package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import com.graphql.deliveryShare2.sample.AboutRestaurant.DeliverylocEntity;
import com.google.gson.Gson;
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
import java.util.ArrayList;
import java.util.Arrays;
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
    
    @Override
    public RestaurantEntity getRestaurant(@Param("seq") int seq){
        //RestaurantEntity re= new RestaurantEntity();
        EntityManager em5 = emf.createEntityManager();
        RestaurantEntity re = em5.createQuery("SELECT c"
        +" FROM RestaurantEntity AS c"
        +" WHERE c.seq = :seq "
        ,RestaurantEntity.class).setParameter("seq", seq).getSingleResult();
        
        List<String> obj=Arrays.asList(re.getDayoff().split(","));
        String json = new Gson().toJson(obj);

        
        re.setDayoff(json);
        
        EntityManager em6 = emf.createEntityManager();
        List<LikesEntity> likes = em6.createQuery("SELECT l"
        +" FROM LikesEntity AS l"
        +" WHERE l.resseq = :seq "
        ,LikesEntity.class).setParameter("seq", seq).getResultList();
        System.out.println("좋아요 "+likes);
        System.out.println("좋아요개수"+likes.size());
        int likescnt = likes.size();
        System.out.println("좋아요개수저장"+likescnt);
        re.setLikescount(likescnt);
        System.out.println("저장됐나"+re.getLikescount());
        re.setIsliked(false);
        for (int i=0; i<likes.size(); i++){
            LikesEntity le = likes.get(i);
            if (le.getUserseq()==10){
                re.setIsliked(true);
            }
        }

        EntityManager em7 = emf.createEntityManager();
        List<DeliverylocEntity> deliverylocs = em7.createQuery("SELECT d"
        +" FROM DeliverylocEntity AS d"
        +" WHERE d.resseq=:seq "
        ,DeliverylocEntity.class).setParameter("seq", seq).getResultList();
        re.setDeliveryloc(deliverylocs);
        
        EntityManager em4 = emf.createEntityManager();
        List<ResReviewEntity> results = em4.createQuery("SELECT v"
    +" FROM ResReviewEntity AS v"
    +" LEFT OUTER JOIN RestaurantEntity AS r"
    +" ON r.seq=v.restaurant"
    +" WHERE v.resseq = :resseq "
    ,ResReviewEntity.class).setParameter("resseq", seq).getResultList();

    for (int i=0; i<results.size();i++){
        
        ResReviewEntity rre= results.get(i);
        //re = rre.getRestaurant();
        
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