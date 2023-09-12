package com.noq.backend.repository.cosmos;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.noq.backend.models.Bed;
import com.noq.backend.models.cosmos.BedCosmos;
import com.noq.backend.models.cosmos.HostCosmos;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BedRepositoryCosmos extends ReactiveCosmosRepository<BedCosmos, String> {
    Flux<BedCosmos> findBedCosmosByHost(HostCosmos host);
}
