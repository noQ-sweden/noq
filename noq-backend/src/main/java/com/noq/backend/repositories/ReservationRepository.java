package com.noq.backend.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository {

    // TODO Declare & Implement Methods

    /*Todo maybe update using QUERY is better then save() for performance?*/
//    @Query("UPDATE c SET c.status = @newStatus WHERE c.reservationId = @reservationId")
//    Mono<Reservation> updateReservationStatus(@Param("reservationId") String reservationId, @Param("newStatus") Reservation.Status newStatus);

}
