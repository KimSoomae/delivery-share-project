package com.graphql.deliveryShare2.sample;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;
@NoRepositoryBean

public interface CallingCustomRepository {
    List<CallingEntity> getNearCalls();
}
