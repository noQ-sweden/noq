package com.noq.backend.repository.cosmos;

import com.azure.cosmos.models.PartitionKey;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.noq.backend.models.cosmos.HostCosmos;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface HostRepositoryCosmos extends ReactiveCosmosRepository<HostCosmos, String> {
    Mono<HostCosmos> findByHostId(String hostId);
}
