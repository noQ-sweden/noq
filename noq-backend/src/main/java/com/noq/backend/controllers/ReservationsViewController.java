package com.noq.backend.controllers;

import com.noq.backend.DTO.AddressDTO;
import com.noq.backend.DTO.ReservationsViewDTO;
import com.noq.backend.exeptions.ReservationNotFoundException;
import com.noq.backend.models.Reservation;
import com.noq.backend.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> handleNoReservationsException(ReservationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    private static ReservationsViewDTO toDTO(Reservation reservation) {

        AddressDTO addressDTO = new AddressDTO(
                reservation.getHost().getAddress().getStreet(),
                reservation.getHost().getAddress().getStreetNum(),
                reservation.getHost().getAddress().getPostalCode(),
                reservation.getHost().getAddress().getCityName());

        return new ReservationsViewDTO(
                reservation.getReservationId(),
                reservation.getHost().getName(),
                reservation.getHost().getImage(),
                addressDTO);
    }

}