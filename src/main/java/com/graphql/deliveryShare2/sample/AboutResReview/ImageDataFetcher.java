package com.graphql.deliveryShare2.sample.AboutResReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class ImageDataFetcher {
    @Autowired
    private ImageRepository imageRepository;
}
