package com.graphql.deliveryShare2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.locationtech.jts.geom.*;
import java.io.StringReader;
import java.io.Reader;
import org.locationtech.jts.io.WKTReader;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@EnableScheduling
public class DeliveryShare2Application extends SpringBootServletInitializer{
	
	public static void main(String[] args) 

    {
        System.out.println("Hello ds");
		SpringApplication.run(DeliveryShare2Application.class, args);

	}

    @Override 
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DeliveryShare2Application.class); 
}



	//@Bean
    //GraphQLSchema schema() {
    //    return GraphQLSchema.newSchema()
    //        .query(GraphQLObjectType.newObject()
    //            .name("query")
    //            .field(field -> field
    //                .name("test")
    //                .type(Scalars.GraphQLString)
    //                .dataFetcher(environment -> "response")
    //            )
    //            .build())
    //        .build();
    //}

}

