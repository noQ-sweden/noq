package com.noq.backend.repositories;

import com.noq.backend.models.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HostRepository extends JpaRepository<Host, UUID> {

    @Query("SELECT h FROM HOST h WHERE h.city = :city")
    List<Host> getAllByCity(String city);
}
