package com.noq.backend.services.cosmos;

import com.noq.backend.models.cosmos.HostCosmos;
import reactor.core.publisher.Mono;

public interface HostCosmosServiceI {
    Mono<HostCosmos> findByHostId(String id);
}
