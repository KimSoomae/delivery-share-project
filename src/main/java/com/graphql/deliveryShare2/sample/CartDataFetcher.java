package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class CartDataFetcher {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private SelectedMenuRepository selectedMenuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public CartDataFetcher(CartRepository cartRepository, SelectedMenuRepository selectedMenuRepository, UserRepository userRepository){
      this.cartRepository=cartRepository;
      this.selectedMenuRepository=selectedMenuRepository;
      this.userRepository=userRepository;
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

    public DataFetcher<?> createCart(){
      return environment -> {
        CartEntity cartEntity = new CartEntity();
        int selectedmenuSeq = environment.getArgument("selectedmenuSeq");
        int userSeq = environment.getArgument("userSeq");
        
        cartEntity.setUserSeq(userSeq);
        cartEntity.setSelectedmenuSeq(selectedmenuSeq);

        cartRepository.save(cartEntity);

        return cartEntity;
      };
    }

    public DataFetcher<?> updateCart(){
      return environment -> {
        int seq=environment.getArgument("seq");
        int count = environment.getArgument("count");
        CartEntity cartEntity= cartRepository.findBySeq(seq);
        cartEntity.getSelectedmenu().setCount(count);

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
