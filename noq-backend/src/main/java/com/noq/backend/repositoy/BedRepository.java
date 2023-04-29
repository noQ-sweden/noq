package com.noq.backend.repositoy;

import com.noq.backend.models.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedRepository extends JpaRepository<Bed, Long> {
}
