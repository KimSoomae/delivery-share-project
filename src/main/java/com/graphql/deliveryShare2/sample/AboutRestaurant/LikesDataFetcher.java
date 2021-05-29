package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class LikesDataFetcher {
    @Autowired
    private LikesRepository likesRepository;
}
