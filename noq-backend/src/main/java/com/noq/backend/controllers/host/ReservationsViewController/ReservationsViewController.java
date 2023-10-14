package com.noq.backend.controllers.host.ReservationsViewController;

import com.noq.backend.exceptions.ReservationNotFoundException;
import com.noq.backend.models.Address;
import com.noq.backend.models.Reservation;
import com.noq.backend.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/host/reservations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationsViewController {
    private final ReservationService service;

    @GetMapping
    public Mono<ResponseEntity<ReservationsViewDTO>> getReservation(@RequestParam String userId) {
        return service
                .getReservationByUserId(userId)
                .mapNotNull(reservation -> ResponseEntity.ok(toDTO(reservation)));
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> handleNoReservationsException(ReservationNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    private ReservationsViewDTO toDTO(Reservation reservation) {
        Address address = reservation.getHost().getAddress();
        ReservationsViewDTO.AddressDTO addressDTO = new ReservationsViewDTO.AddressDTO(
                address.getStreet(),
                address.getStreetNum(),
                address.getPostalCode(),
                address.getCityName());

        return new ReservationsViewDTO(
                reservation.getReservationId(),
                reservation.getHost().getName(),
                reservation.getHost().getImage(),
                addressDTO);
    }
}
