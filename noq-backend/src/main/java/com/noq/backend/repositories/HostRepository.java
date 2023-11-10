package com.noq.backend.repositories;

import com.noq.backend.models.Host;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HostRepository {
    Optional<Host> findByHostId(String hostId);
}
