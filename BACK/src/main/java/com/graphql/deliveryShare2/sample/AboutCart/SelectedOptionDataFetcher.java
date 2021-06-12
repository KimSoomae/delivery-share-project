package com.graphql.deliveryShare2.sample.AboutCart;

import javax.transaction.Transactional;


import com.graphql.deliveryShare2.sample.AboutRestaurant.OptionItemEntity;
import com.graphql.deliveryShare2.sample.AboutRestaurant.OptionItemRepository;
import com.graphql.deliveryShare2.sample.AboutRestaurant.MenuRepository;
import com.graphql.deliveryShare2.sample.AboutRestaurant.MenuEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;



@Component
public class SelectedOptionDataFetcher{
    @Autowired
    private SelectedOptionRepository selectedOptionRepository;

    @Autowired
    private SelectedMenuRepository selectedMenuRepository;

    @Autowired
    private OptionItemRepository optionItemRepository;

    @Autowired
    private MenuRepository menuRepository;


    @Autowired
    public SelectedOptionDataFetcher(SelectedOptionRepository selectedOptionRepository, SelectedMenuRepository selectedMenuRepository, OptionItemRepository optionItemRepository, MenuRepository menuRepository){
      this.selectedOptionRepository=selectedOptionRepository;
      this.selectedMenuRepository=selectedMenuRepository;
      this.optionItemRepository=optionItemRepository;
      this.menuRepository=menuRepository;
     }

    public DataFetcher<?> allSelectedOptions () {
      return environment -> {
        return selectedOptionRepository.findAll();
      };
    }
  
    public DataFetcher<?> SelectedOption () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return selectedOptionRepository.findBySeq(seq);
      };
    }

     public SelectedMenuEntity getSelectedMenu(SelectedOptionEntity selectedOptionEntity){
      return selectedMenuRepository.findBySeq(selectedOptionEntity.getSelectedMenu().getSeq());
     }

     public OptionItemEntity getOptionItem(SelectedOptionEntity selectedOptionEntity){
        return optionItemRepository.findBySeq(selectedOptionEntity.getOptionItem().getSeq());
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
    
  
}
