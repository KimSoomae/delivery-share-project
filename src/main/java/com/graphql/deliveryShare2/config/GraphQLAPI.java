package com.graphql.deliveryShare2.config;
import java.io.IOException;
import java.net.URL;
//import com.coxautodev.graphql.tools.SchemaParser;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.*;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.graphql.deliveryShare2.sample.AboutCall.CallLocationDataFetcher;
import com.graphql.deliveryShare2.sample.AboutCall.CallingDataFetcher;
import com.graphql.deliveryShare2.sample.AboutCall.OrderDataFetcher;
import com.graphql.deliveryShare2.sample.AboutCart.CartDataFetcher;
import com.graphql.deliveryShare2.sample.AboutCart.SelectedMenuDataFetcher;
import com.graphql.deliveryShare2.sample.AboutChat.ChatDataFetcher;
import com.graphql.deliveryShare2.sample.AboutChat.MessageDataFetcher;
import com.graphql.deliveryShare2.sample.AboutResReview.ReplyDataFetcher;
import com.graphql.deliveryShare2.sample.AboutResReview.ResReviewDataFetcher;
import com.graphql.deliveryShare2.sample.AboutRestaurant.LocationDataFetcher;
import com.graphql.deliveryShare2.sample.AboutRestaurant.MenuDataFetcher;
import com.graphql.deliveryShare2.sample.AboutRestaurant.OptionDataFetcher;
import com.graphql.deliveryShare2.sample.AboutRestaurant.OptionItemDataFetcher;
import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantDataFetcher;
import com.graphql.deliveryShare2.sample.AboutRestaurant.RunTimeDataFetcher;
import com.graphql.deliveryShare2.sample.AboutUser.ReportDataFetcher;
import com.graphql.deliveryShare2.sample.AboutUser.UserDataFetcher;
import com.graphql.deliveryShare2.sample.AboutUser.UserReviewDataFetcher;
import com.graphql.deliveryShare2.sample.AboutRestaurant.LikesDataFetcher;
import com.graphql.deliveryShare2.sample.LoginUser.LoginUserDataFetcher;

import org.hibernate.jpa.event.internal.CallbackRegistryImplementor;
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
  @Autowired UserReviewDataFetcher dataFetcher6;
  @Autowired UserDataFetcher dataFetcher;
  @Autowired OptionDataFetcher optionDataFetcher;
  @Autowired OptionItemDataFetcher optionItemDataFetcher;
  @Autowired OrderDataFetcher orderDataFetcher;
  @Autowired ReplyDataFetcher replyDataFetcher;
  @Autowired ReportDataFetcher reportDataFetcher;
  @Autowired CallingDataFetcher callingDataFetcher;
  @Autowired CartDataFetcher cartDataFetcher;
  @Autowired ChatDataFetcher chatDataFetcher;
  @Autowired LocationDataFetcher locationDataFetcher;
  @Autowired MenuDataFetcher menuDataFetcher;
  @Autowired MessageDataFetcher messageDataFetcher;
  @Autowired CallLocationDataFetcher callLocationDataFetcher;
  @Autowired LikesDataFetcher likesDataFetcher;
  @Autowired LoginUserDataFetcher loginUserDataFetcher;
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
  //private static GraphQLSchema buildSchema() {
  //  SelectedMenuRepository selectedMenuRepository = new SelectedMenuRepository();
  //  return SchemaParser.newParser()
	//.file("schema.graphqls")
	//.resolvers(new Query(selectedMenuRepository), new Mutation(linkRepository))
	//.build()
	//.makeExecutableSchema();
//}
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
      .dataFetcher("getRestaurants", dataFetcher5.getRestaurants())   
      .dataFetcher("allUserReviews", dataFetcher6.allUserReviews())
      .dataFetcher("UserReview", dataFetcher6.UserReview())  
      .dataFetcher("allOptions", optionDataFetcher.allOptions())
      .dataFetcher("Option", optionDataFetcher.Option()) 
      .dataFetcher("allOptionItems",optionItemDataFetcher.allOptionItems())
      .dataFetcher("OptionItem", optionItemDataFetcher.OptionItem()) 
      .dataFetcher("allOrders",orderDataFetcher.allOrders())
      .dataFetcher("Order",orderDataFetcher.Order()) 
      .dataFetcher("allReplies",replyDataFetcher.allReplies())
      .dataFetcher("Reply",replyDataFetcher.Reply()) 
      .dataFetcher("allReports",reportDataFetcher.allReports())
      .dataFetcher("Report",reportDataFetcher.Report()) 
      .dataFetcher("allCallings", callingDataFetcher.allCallings())
      .dataFetcher("Calling",callingDataFetcher.Calling())
      .dataFetcher("allCarts", cartDataFetcher.allCarts())
      .dataFetcher("Cart",cartDataFetcher.Cart())
      .dataFetcher("allChats", chatDataFetcher.allChats())
      .dataFetcher("Chat",chatDataFetcher.Chat())
      .dataFetcher("allLocations", locationDataFetcher.allLocations())
      .dataFetcher("Location",locationDataFetcher.Location())
      .dataFetcher("allMenues", menuDataFetcher.allMenues())
      .dataFetcher("Menu",menuDataFetcher.Menu())
      .dataFetcher("allMessages", messageDataFetcher.allMessages())
      .dataFetcher("Message",messageDataFetcher.Message())     
      .dataFetcher("getNearCalls",callingDataFetcher.getNearCalls())
      .dataFetcher("allCallLocations",callLocationDataFetcher.allCallLocations())
      .dataFetcher("CallLocation",callLocationDataFetcher.CallLocation())
      .dataFetcher("getResReviews", dataFetcher4.getResReviews())   
      .dataFetcher("getReviewCount", dataFetcher4.getReviewCount())   
      .dataFetcher("getRestaurant", dataFetcher5.getRestaurant())  
      .dataFetcher("OptionByMenu", optionDataFetcher.OptionByMenu()) 
      .dataFetcher("MenuByRes",menuDataFetcher.MenuByRes())
      .dataFetcher("getUser",dataFetcher1.getUser())
      .dataFetcher("getLikedRestaurants", dataFetcher5.getLikedRestaurants())  
      
      
      
    )
    .type(
      TypeRuntimeWiring
      .newTypeWiring("Mutation")
      .dataFetcher("createSelectedMenu",dataFetcher3.createSelectedMenu())
      .dataFetcher("updateSelectedMenu",dataFetcher3.updateSelectedMenu())
      .dataFetcher("deleteSelectedMenu",dataFetcher3.deleteSelectedMenu())
      .dataFetcher("createCart",cartDataFetcher.createCart())
      .dataFetcher("updateCart", cartDataFetcher.updateCart())
      .dataFetcher("deleteCart", cartDataFetcher.deleteCart())
      .dataFetcher("login",loginUserDataFetcher.login())
      .dataFetcher("toggleLikeRestaurant",likesDataFetcher.toggleLikeRestaurant())
    )
    .build();
  }

}