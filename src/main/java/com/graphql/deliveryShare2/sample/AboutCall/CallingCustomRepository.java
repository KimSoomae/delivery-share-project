package com.graphql.deliveryShare2.sample.AboutCall;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;
@NoRepositoryBean

public interface CallingCustomRepository {
    List<CallingEntity> getNearCalls();
    List<CallingEntity> getNearCalls(Double latitude, Double logitude);
}
