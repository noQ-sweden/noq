package com.noq.backend.controllers;

import com.noq.backend.DTO.ReservationDTO;
import com.noq.backend.DTO.ReservationsViewDTO;
import com.noq.backend.models.Reservation;
import com.noq.backend.services.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationsViewController {

    private final ReservationService reservationService;

    public ReservationsViewController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


   @GetMapping("/{userId}")
    public ReservationsViewDTO getReservation(@PathVariable String userId) {
        return toDTO(reservationService.getReservationByUserId(userId));
    }



    private static ReservationsViewDTO toDTO(Reservation reservation) {
        return new ReservationsViewDTO(
                reservation.getReservationId(),
                reservation.getHost().getName(),
                reservation.getHost().getImage(),
                reservation.getHost().getAddress());
    }

}