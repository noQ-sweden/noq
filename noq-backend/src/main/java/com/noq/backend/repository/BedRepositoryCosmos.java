package com.noq.backend.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.noq.backend.models.Bed;
import com.noq.backend.models.HostCosmos;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BedRepositoryCosmos extends ReactiveCosmosRepository<Bed, String> {
    Flux<Bed> findBedsByHost(HostCosmos host);
}
