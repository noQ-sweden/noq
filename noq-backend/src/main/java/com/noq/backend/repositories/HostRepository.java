package com.noq.backend.repositories;

import com.noq.backend.models.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<Host, String> {
    @Override
    Optional<Host> findById(String id);
}
