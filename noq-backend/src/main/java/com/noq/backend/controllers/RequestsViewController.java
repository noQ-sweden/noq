package com.noq.backend.controllers;

import com.noq.backend.DTO.RequestsViewDTO;
import com.noq.backend.DTO.ReservationDTO;
import com.noq.backend.models.Reservation;
import com.noq.backend.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RequestsViewController {

    private final ReservationService reservationService;

    public RequestsViewController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/get-reservations/{hostId}")
    public List<RequestsViewDTO> getReservationsByHostId(@PathVariable String hostId) {
        return reservationService.getReservationsByHostId(hostId)
                .stream()
                .map(RequestsViewController::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/get-pending/{hostId}")
    public List<RequestsViewDTO> getPendingByHostId(@PathVariable String hostId) {
        return reservationService.getReservationsByHostIdStatusPending(hostId)
                .stream()
                .map(RequestsViewController::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/approve-reservations/{hostId}")
    public ResponseEntity<String> approveReservations(@RequestBody List<String> reservationsId, @PathVariable String hostId) {
        reservationService.approveReservations(reservationsId);
        String responseMessage = " approved reservation for ";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping("/get-approved/{hostId}")
    public List<RequestsViewDTO> getApprovedByHostId(@PathVariable String hostId) {
        return reservationService.getReservationsByHostIdStatusReserved(hostId)
                .stream()
                .map(RequestsViewController::toDTO)
                .collect(Collectors.toList());
    }


    private static RequestsViewDTO toDTO(Reservation reservation) {
        RequestsViewDTO.UserDTO user = new RequestsViewDTO.UserDTO(
                reservation.getUser().getId(),
                reservation.getUser().getName());
        RequestsViewDTO.ReservationDTO res = new RequestsViewDTO.ReservationDTO(
                reservation.getReservationId(),
                reservation.getStatus(),
                user);

        return new RequestsViewDTO(
                res
        );
    }

}
