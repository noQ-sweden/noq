package com.noq.backend.repositories;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.models.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CosmosRepository<Reservation, String> {
    Optional<Reservation> findReservationCosmosByReservationId(String id);

    Optional<Reservation> findReservationCosmosByUser(User user);

    Iterable<Reservation> findReservationCosmosByHost(Host host);

    Iterable<Reservation> findReservationCosmosByHostAndStatus(Host host, Reservation.Status status);

    /*Todo maybe update using QUERY is better then save() for performance?*/
//    @Query("UPDATE c SET c.status = @newStatus WHERE c.reservationId = @reservationId")
//    Mono<Reservation> updateReservationStatus(@Param("reservationId") String reservationId, @Param("newStatus") Reservation.Status newStatus);

}
