package com.noq.backend.controllers;

import com.noq.backend.DTO.HostDTO;
import com.noq.backend.DTO.ReservationDTO;
import com.noq.backend.models.CreateReservation;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.services.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @GetMapping("/{userId}")
    public ReservationDTO getReservation(@PathVariable String userId) {
        return toReservationDTO(reservationService.getReservationByUserId(userId));
    }

    @PostMapping("/create")
    public ReservationDTO createReservation(@RequestBody CreateReservation createReservation) {
        return toReservationDTO(reservationService.createReservation(createReservation));
    }
    @GetMapping("/get-reservations/{hostId}")
    public List<ReservationDTO> getReservationsByHostId(@PathVariable String hostId) {
        return reservationService.getReservationsByHostId(hostId)
                .stream()
                .map(ReservationController::toReservationDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/approve-reservations/{hostId}")
    public List<ReservationDTO> approveReservations(@RequestBody List<String> reservationsId) {
        return reservationService.approveReservations(reservationsId)
                .stream()
                .map(ReservationController::toReservationDTO)
                .collect(Collectors.toList());
    }


    private static ReservationDTO toReservationDTO(Reservation reservation) {
        return new ReservationDTO(
                reservation.getReservationId(),
                reservation.getHost(),
                reservation.getUser(),
                reservation.getStatus());
    }
}