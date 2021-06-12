package com.graphql.deliveryShare2.sample.AboutCall;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;
import java.util.TimerTask;
@NoRepositoryBean

public interface CallingCustomRepository {
    List<CallingEntity> getNearCalls(Double latitude, Double logitude);
   
}
