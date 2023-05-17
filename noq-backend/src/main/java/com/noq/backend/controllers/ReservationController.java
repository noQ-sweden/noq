package com.noq.backend.controllers;

import com.noq.backend.DTO.ReservationDTO;
import com.noq.backend.models.CreateReservation;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.services.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @GetMapping("/hello")
    public String testHello() {
        return "hello";
    }

    @PostMapping("/create")
    public ReservationDTO createReservation(@RequestBody CreateReservation createReservation)
    {
        Host host = createReservation.getHost();
        String userId = createReservation.getUserId();
        return toReservationDTO(reservationService.createReservation(host, userId));
    }


    private static ReservationDTO toReservationDTO(Reservation reservation) {
        return new ReservationDTO(
                reservation.getId(),
                reservation.getHost(),
                reservation.getUser(),
                reservation.getStatus());
    }
}