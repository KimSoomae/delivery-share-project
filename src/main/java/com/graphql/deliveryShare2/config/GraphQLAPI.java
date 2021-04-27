package com.graphql.deliveryShare2.config;
import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.graphql.deliveryShare2.sample.UserDataFetcher;
import com.graphql.deliveryShare2.sample.RunTimeDataFetcher;
import com.graphql.deliveryShare2.sample.SelectedMenuDataFetcher;
import com.graphql.deliveryShare2.sample.ResReviewDataFetcher;
import com.graphql.deliveryShare2.sample.RestaurantDataFetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;

@Component
public class GraphQLAPI {

  @Autowired UserDataFetcher dataFetcher1;
  @Autowired RunTimeDataFetcher dataFetcher2;
  @Autowired SelectedMenuDataFetcher dataFetcher3;
  @Autowired ResReviewDataFetcher dataFetcher4;
  @Autowired RestaurantDataFetcher dataFetcher5;

  private GraphQL graphQL;

  @Value("classpath:static/graphql/schema.graphqls") 
  Resource resource;

  // (3)
  @Bean 
  public GraphQL graphQL() {
    return graphQL;
  }

  // (1)
  @PostConstruct
  public void init() throws IOException {
    URL url = resource.getURL();
    String sdl = Resources.toString(url, Charsets.UTF_8);
    GraphQLSchema graphQLSchema = buildSchema(sdl);
    this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  // (2)
  private GraphQLSchema buildSchema(String sdl) {
    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
    RuntimeWiring runtimeWiring = buildWiring();
    SchemaGenerator schemaGenerator = new SchemaGenerator();
    return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
  }

  private RuntimeWiring buildWiring() {
    return RuntimeWiring.newRuntimeWiring()
    .type(
      TypeRuntimeWiring
      .newTypeWiring("Query")
      .dataFetcher("allUsers", dataFetcher1.allUsers())
      .dataFetcher("User", dataFetcher1.User())  
      .dataFetcher("allRunTimes", dataFetcher2.allRunTimes())
      .dataFetcher("RunTime", dataFetcher2.RunTime())  
      .dataFetcher("allSelectedMenus", dataFetcher3.allSelectedMenus())  
      .dataFetcher("SelectedMenu", dataFetcher3.SelectedMenu())  
      .dataFetcher("allResReviews", dataFetcher4.allResReviews())  
      .dataFetcher("ResReview", dataFetcher4.ResReview())  
      .dataFetcher("allRestaurants", dataFetcher5.allRestaurants())  
      .dataFetcher("Restaurant", dataFetcher5.Restaurant())  


    )

    //.type(
    //  TypeRuntimeWiring
    //  .newTypeWiring("Query")
    //  .dataFetcher("allRunTimes", dataFetcher2.allRunTimes())
    //  .dataFetcher("RunTime", dataFetcher2.RunTime())     
    //)


    .build();
  }

}

