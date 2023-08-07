package com.noq.backend.controllers;

import com.noq.backend.DTO.HostDTO;
import com.noq.backend.DTO.ReservationDTO;
import com.noq.backend.DTO.ReservationsViewDTO;
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


/*    @GetMapping("/{userId}")
    public ReservationDTO getReservation(@PathVariable String userId) {
        return toReservationDTO(reservationService.getReservationByUserId(userId));
    }


    @GetMapping("/get-reservations/{hostId}")
    public List<ReservationDTO> getReservationsByHostId(@PathVariable String hostId) {
        return reservationService.getReservationsByHostIdStatusPending(hostId)
                .stream()
                .map(ReservationController::toReservationDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/approve-reservations/{hostId}")
    public List<ReservationDTO> approveReservations(@RequestBody List<String> reservationsId, @PathVariable String hostId) {
        return reservationService.approveReservations(reservationsId)
                .stream()
                .map(ReservationController::toReservationDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/get-approved/{hostId}")
    public List<ReservationDTO> getApprovedByHostId(@PathVariable String hostId) {
        return reservationService.getReservationsByHostIdStatusReserved(hostId)
                .stream()
                .map(ReservationController::toDTO)
                .collect(Collectors.toList());
    }*/

    private static ReservationsViewDTO toDTO(ReservationDTO reservationDTO) {
        return new ReservationsViewDTO(
                reservationDTO.reservationId(),
                reservationDTO.hostDTO().name(),
                reservationDTO.hostDTO().image(),
                reservationDTO.hostDTO().address());
    }

}