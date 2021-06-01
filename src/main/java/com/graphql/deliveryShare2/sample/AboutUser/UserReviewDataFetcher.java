package com.graphql.deliveryShare2.sample.AboutUser;

import javax.transaction.Transactional;

import com.graphql.deliveryShare2.sample.AboutUser.UserRepository;
import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class UserReviewDataFetcher {
    @Autowired
    private UserReviewRepository userReviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserReviewDataFetcher(UserReviewRepository userReviewRepository, UserRepository userRepository){
      this.userReviewRepository=userReviewRepository;
      this.userRepository=userRepository;
    
    }
  
    public DataFetcher<?> allUserReviews () {
      return environment -> {
        return userReviewRepository.findAll();
      };
    }
  
    public DataFetcher<?> UserReview () {
      return environment -> {
        int toseq = environment.getArgument("toseq");
        return userReviewRepository.findByToseq(toseq);
      };
    }


    public DataFetcher<?> UserReviewBySeq () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return userReviewRepository.findBySeq(seq);
      };
    }
  

    public UserEntity getUser(UserReviewEntity userreviewEntity) {
      return userRepository.findBySeq(userreviewEntity.getUser().getSeq());
    }

    public UserEntity getToUser(UserReviewEntity userreviewEntity) {
      return userRepository.findBySeq(userreviewEntity.getUser().getSeq());
    }


    //public DataFetcher<?> createUserReview(){
    //  return environment -> {
    //    UserReviewEntity userReviewEntity = new UserReviewEntity();
    //    String content=environment.getArgument("content");
    //    float rate = environment.getArgument("rate");
    //    int fromseq = environment.getArgument("fromseq");
    //    int toseq = environment.getArgument("toseq");

        
      //  userReviewEntity.setContent(content);
      //  userReviewEntity.setRate(rate);
      //  userReviewEntity.setFromseq(fromseq);
      //  userReviewEntity.setToseq(toseq);

       // userReviewRepository.save(userReviewEntity);

        //return userReviewEntity;
      //};
    //}

}
