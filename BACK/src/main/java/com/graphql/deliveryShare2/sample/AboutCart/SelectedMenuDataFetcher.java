package com.graphql.deliveryShare2.sample.AboutCart;
import java.util.List;
import java.util.ArrayList;

import javax.transaction.Transactional;

import com.graphql.deliveryShare2.sample.AboutCall.CallingRepository;
import com.graphql.deliveryShare2.sample.AboutRestaurant.MenuEntity;
import com.graphql.deliveryShare2.sample.AboutRestaurant.MenuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;



@Component
public class SelectedMenuDataFetcher{
    @Autowired
    private SelectedMenuRepository selectedMenuRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CallingRepository callingRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private SelectedOptionRepository selectedOptionRepository;

    @Autowired
    public SelectedMenuDataFetcher(SelectedMenuRepository selectedMenuRepository, MenuRepository menuRepository, CallingRepository callingRepository, CartRepository cartRepository, SelectedOptionRepository selectedOptionRepository){
      this.selectedMenuRepository=selectedMenuRepository;
      this.menuRepository=menuRepository;
      this.callingRepository=callingRepository;
      this.cartRepository = cartRepository;
      this.selectedOptionRepository = selectedOptionRepository;
     }

    public DataFetcher<?> allSelectedMenus () {
      return environment -> {
        return selectedMenuRepository.findAll();
      };
    }
  
    public DataFetcher<?> SelectedMenu () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return selectedMenuRepository.findBySeq(seq);
      };
    }

     public CartEntity getCart(SelectedMenuEntity selectedMenuEntity){
      return cartRepository.findBySeq(selectedMenuEntity.getCart().getSeq());
     }

    

    public MenuEntity getMenu(SelectedMenuEntity selectedMenuEntity){
      return menuRepository.findBySeq(selectedMenuEntity.getMenu().getSeq());
    }

   
    public DataFetcher<?> createSelectedMenu(){
      return environment -> {
        SelectedMenuEntity selectedMenuEntity = new SelectedMenuEntity();
        int menuSeq=environment.getArgument("menuSeq");
        int count = environment.getArgument("count");
        
        selectedMenuEntity.setMenuSeq(menuSeq);
        selectedMenuEntity.setCount(count);

        selectedMenuRepository.save(selectedMenuEntity);

        return selectedMenuEntity;
      };
    }

    public DataFetcher<?> updateSelectedMenu(){
      return environment -> {
        int seq=environment.getArgument("seq");
        SelectedMenuEntity selectedMenuEntity= selectedMenuRepository.findBySeq(seq);
        int count = environment.getArgument("count");
      
        selectedMenuEntity.setCount(count);

        selectedMenuRepository.save(selectedMenuEntity);

        return selectedMenuEntity;
      };
    }
   
    public DataFetcher<?> deleteSelectedMenu(){
      return environment -> {
        int seq=environment.getArgument("seq");
        selectedMenuRepository.deleteBySeq(seq);
        return true;
      };
    }
    //public SelectedMenuEntity createSelectedMenu(Integer menuSeq, Integer count){
    //    SelectedMenuEntity selectedMenuEntity = new SelectedMenuEntity();
    //    selectedMenuEntity.setMenu(new MenuEntity(menuSeq));
    //    selectedMenuEntity.setCount(count);

     //   selectedMenuRepository.save(selectedMenuEntity);

     //   return selectedMenuEntity;
   // }

   public List<SelectedOptionEntity> getSelectedOption(SelectedMenuEntity selectedMenuEntity) {
    return selectedOptionRepository.findAll();
    

  }
    
  
}
