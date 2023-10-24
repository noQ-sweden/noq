package com.noq.backend.repositories;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BedRepository extends CosmosRepository<Bed, String> {
    Optional<Bed> findBedCosmosByHost(Host host);
}
