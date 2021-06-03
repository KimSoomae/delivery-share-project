package com.graphql.deliveryShare2.sample.AboutCart;

import com.graphql.deliveryShare2.sample.AboutUser.UserRepository;
import com.graphql.deliveryShare2.sample.AboutCall.CallingRepository;
import com.graphql.deliveryShare2.sample.AboutCall.CallingEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import java.util.List;

@Component
public class CartDataFetcher {
    @Autowired
    private CartRepository cartRepository;

    //@Autowired
    //private SelectedMenuRepository selectedMenuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CallingRepository callingRepository;

    @Autowired
    public CartDataFetcher(CartRepository cartRepository, UserRepository userRepository, CallingRepository callingRepository){
      this.cartRepository=cartRepository;
      //this.selectedMenuRepository=selectedMenuRepository;
      this.userRepository=userRepository;
      this.callingRepository = callingRepository;
    }

    public DataFetcher<?> allCarts () {
      return environment -> {
        return cartRepository.findAll();
      };
    }
  
    public DataFetcher<?> Cart () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return cartRepository.findBySeq(seq);
      };
    }

    //public List<SelectedMenuEntity> getSelectedMenu(CartEntity cartEntity) {
    //  return selectedMenuRepository.findAll();

    //}

    //public CallingEntity getCall(CartEntity cartEntity){
    //  return callingRepository.findBySeq(cartEntity.getCall().getSeq());
    //}

    public DataFetcher<?> createCart(){
      return environment -> {
        CartEntity cartEntity = new CartEntity();
        //int selectedmenuSeq = environment.getArgument("selectedmenuSeq");
        int userSeq = environment.getArgument("userSeq");
        
        cartEntity.setUserSeq(userSeq);
        //cartEntity.setSelectedmenuSeq(selectedmenuSeq);

        cartRepository.save(cartEntity);

        return cartEntity;
      };
    }

    public DataFetcher<?> updateCart(){
      return environment -> {
        int seq=environment.getArgument("seq");
        int count = environment.getArgument("count");
        CartEntity cartEntity= cartRepository.findBySeq(seq);
        //cartEntity.getSelectedmenu().setCount(count);

        cartRepository.save(cartEntity);
        
      
  
        return cartEntity;
      };
    }

    public DataFetcher<?> deleteCart(){
      return environment -> {
        int seq=environment.getArgument("seq");
        cartRepository.deleteBySeq(seq);
        return true;
      };
    }
  
}