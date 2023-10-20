package com.noq.backend.repositories;

import com.azure.spring.data.cosmos.repository.Query;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.models.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReservationRepository extends ReactiveCosmosRepository<Reservation, String> {
    Mono<Reservation> findReservationCosmosByReservationId(String id);
    Mono<Reservation> findReservationCosmosByUser(User user);
    Flux<Reservation> findReservationCosmosByHost(Host host);
    Flux<Reservation> findReservationCosmosByHostAndStatus(Host host, Reservation.Status status);

    /*Todo maybe update using QUERY is better then save() for performance?*/
//    @Query("UPDATE c SET c.status = @newStatus WHERE c.reservationId = @reservationId")
//    Mono<Reservation> updateReservationStatus(@Param("reservationId") String reservationId, @Param("newStatus") Reservation.Status newStatus);

}
