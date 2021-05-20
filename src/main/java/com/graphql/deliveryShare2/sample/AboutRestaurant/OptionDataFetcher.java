package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;

import graphql.schema.DataFetcher;

@Component
public class OptionDataFetcher {
    @Autowired 
    private OptionRepository optionRepository;
  
    @Autowired
    private OptionItemRepository optionItemRepository;

    @Autowired
    public OptionDataFetcher(OptionRepository optionRepository, OptionItemRepository optionItemRepository) {
      this.optionRepository = optionRepository;
      this.optionItemRepository = optionItemRepository;
    }

    public List<OptionItemEntity> getOptionItem(OptionEntity optionEntity) {
      return optionItemRepository.findAll();
      

    }
  
    
    public DataFetcher<?> allOptions () {
      return environment -> {
        return optionRepository.findAll();
      };
    }
  
    public DataFetcher<?> Option () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return optionRepository.findBySeq(seq);
      };
    }

    public DataFetcher<?> OptionByMenu () {
      return environment -> {
        int menuseq = environment.getArgument("menuseq");
        return optionRepository.findByMenuseq(menuseq);
      };
    }

  
}