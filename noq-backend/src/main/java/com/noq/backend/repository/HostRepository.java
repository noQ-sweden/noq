package com.noq.backend.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.noq.backend.models.Host;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface HostRepository extends ReactiveCosmosRepository<Host, String> {
    Mono<Host> findByHostId(String hostId);
}
