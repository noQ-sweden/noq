package com.noq.backend.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BedRepository extends ReactiveCosmosRepository<Bed, String> {
    Flux<Bed> findBedCosmosByHost(Host host);
}
