package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class LikesDataFetcher {
    @Autowired
    private LikesRepository likesRepository;

    public DataFetcher<?> toggleLikeRestaurant () {
        return environment -> {
          int resseq = environment.getArgument("resseq");
          Boolean isLiked = environment.getArgument("isLiked");
          int userseq = environment.getArgument("userseq");
          Long likes = likesRepository.countByResseqAndUserseq(resseq,userseq);
          System.out.println("라이크개수"+likes);

          LikesEntity likesEntity = likesRepository.findByResseqAndUserseq(resseq,userseq);
          System.out.println("라이크테이블"+likesEntity);
          try{
              if (isLiked == true){
                  if (likes==0){
                    LikesEntity likesEntityy = new LikesEntity();
                    likesEntityy.setResseq(resseq);
                    likesEntityy.setUserseq(userseq);
                    likesRepository.save(likesEntityy);
                  }
                  else{

                  }
              }
              else{
                  if (likes>0){
                    System.out.println("라잉크인덱스"+likesEntity.getSeq());
                    likesRepository.deleteBySeq(likesEntity.getSeq());
                  }
                  else{

                  }
              }
             return true;
          }
          catch(Exception e){
              return false;
          }
          
        };
    }
      
  
}
