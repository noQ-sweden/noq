package com.noq.backend.repository.cosmos;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.noq.backend.models.cosmos.UserCosmos;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCosmos extends ReactiveCosmosRepository <UserCosmos, String> {

}
