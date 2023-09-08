package com.noq.backend.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.noq.backend.models.Bed;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepositoryCosmos extends ReactiveCosmosRepository<Bed, String> {
}
