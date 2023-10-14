package com.noq.backend.services;

import com.noq.backend.models.Host;
import reactor.core.publisher.Mono;

public interface HostCosmosServiceI {
    Mono<Host> findByHostId(String id);

    Mono<Host> findById(String id);
}
