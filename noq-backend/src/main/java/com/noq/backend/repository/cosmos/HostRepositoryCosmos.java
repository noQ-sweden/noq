package com.noq.backend.repository.cosmos;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.noq.backend.models.cosmos.HostCosmos;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepositoryCosmos extends ReactiveCosmosRepository<HostCosmos, String> {
}