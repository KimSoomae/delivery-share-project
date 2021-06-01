package com.graphql.deliveryShare2.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    // Cors 정책을 모두 허용으로 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
	public void addViewControllers(ViewControllerRegistry registry) {
          registry.addRedirectViewController("/","index");
         
	}

    
}

