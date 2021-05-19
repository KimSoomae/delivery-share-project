
package com.graphql.deliveryShare2.sample;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean

public interface ResReviewCustomRepository {
    RestaurantEntity getReviewCount(int resseq);
}
