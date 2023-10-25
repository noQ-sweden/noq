package com.noq.backend.repositories;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.noq.backend.models.Host;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HostRepository extends CosmosRepository<Host, String> {
    Optional<Host> findByHostId(String hostId);
}
