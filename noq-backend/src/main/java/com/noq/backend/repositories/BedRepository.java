package com.noq.backend.repositories;

import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BedRepository {
    Optional<Bed> findBedByHost(Host host);
}
