
package com.graphql.deliveryShare2.sample.AboutResReview;
import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantEntity;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean

public interface ResReviewCustomRepository {
    RestaurantEntity getReviewCount(int resseq);
}
