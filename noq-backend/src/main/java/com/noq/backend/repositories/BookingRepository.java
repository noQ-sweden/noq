package com.noq.backend.repositories;

import com.noq.backend.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query("SELECT b FROM BOOKING b WHERE b.userId = :userId")
    List<Booking> findAllByUserId(UUID userId);

//    @Query("SELECT b FROM BOOKING b WHERE b.hostId = :hostId")
    List<Booking> findAllByHostId(UUID hostId);
    Optional<Booking> findByHostId(UUID hostId);
}
