package com.graphql.deliveryShare2.sample.AboutResReview;

import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantEntity;
import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantRepository;
import com.graphql.deliveryShare2.sample.AboutCall.CallingEntity;
import com.graphql.deliveryShare2.sample.AboutCall.CallingRepository;
import com.graphql.deliveryShare2.sample.AboutCall.OrderEntity;
import com.graphql.deliveryShare2.sample.AboutCall.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import graphql.schema.DataFetcher;

@Component
public class ResReviewDataFetcher {
    @Autowired
    private ResReviewRepository resReviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository; 

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private CallingRepository callingRepository; 

    @Autowired
    private OrderRepository orderRepository; 

    @Autowired
    public ResReviewDataFetcher(RestaurantRepository restaurantRepository, ResReviewRepository resReviewRepository, ImageRepository imageRepository, ReplyRepository replyRepository, CallingRepository callingRepository, OrderRepository orderRepository ){
      this.restaurantRepository=restaurantRepository;
      this.resReviewRepository=resReviewRepository;
      this.imageRepository=imageRepository;
      this.replyRepository=replyRepository;
      this.callingRepository=  callingRepository;
      this.orderRepository = orderRepository;

    }
    public DataFetcher<?> allResReviews () {
      return environment -> {
        return resReviewRepository.findAll();
      };
    }
  
    public DataFetcher<?> ResReview () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return resReviewRepository.findBySeq(seq);
      };
    }

    public List<ImageEntity> getImages(ResReviewEntity resReviewEntity) {
      return imageRepository.findAll();
      
    }
    public RestaurantEntity getRestaurant(ResReviewEntity resReviewEntity){
      return restaurantRepository.findBySeq(resReviewEntity.getRestaurant().getSeq());
    }

    public DataFetcher<?> getResReviews () {
      return environment -> {
        int resseq = environment.getArgument("resseq");
        String sortingmethod = environment.getArgument("sortingmethod");
        if(sortingmethod.equals("별점높은순")){
          return resReviewRepository.findByResseqOrderByRateDesc(resseq);
        }

        if (sortingmethod.equals("별점낮은순")){
          return resReviewRepository.findByResseqOrderByRateAsc(resseq);
        }

        return resReviewRepository.findByResseqOrderByCreatedAtDesc(resseq);
        
      };
    }

    public DataFetcher<?> getReviewCount () {
      return environment -> {
        int resseq = environment.getArgument("resseq");
        return resReviewRepository.getReviewCount(resseq);
      };
    }

    public DataFetcher<?> WriteRestaurantReview(){

      return environment -> {
        ResReviewEntity resReviewEntity = new ResReviewEntity();
        CallingEntity callingReviewEntity = new CallingEntity();
        OrderEntity orderReviewEntity = new OrderEntity();
        
        int order_seq = environment.getArgument("order_seq");
        String content = environment.getArgument("content");
        Double rate = environment.getArgument("rate");

        OrderEntity order = orderRepository.findBySeq(order_seq);
        int call_seq = order.getCallSeq();
        CallingEntity call = callingRepository.findBySeq(call_seq);
        int res_seq = call.getResSeq();



        resReviewEntity.setContent(content);
        resReviewEntity.setRate(rate);
        resReviewEntity.setResSeq(res_seq);
        resReviewEntity.setUserSeq(10);

        resReviewEntity.setCreatedAt();

        resReviewRepository.save(resReviewEntity);

        return resReviewEntity;
      };
    }

    

  
}