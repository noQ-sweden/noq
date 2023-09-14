package com.noq.backend.controllers.cosmos;

import com.noq.backend.DTO.AddressDTO;
import com.noq.backend.DTO.ReservationsViewDTO;
import com.noq.backend.exeptions.NoReservationsException;
import com.noq.backend.models.Address;
import com.noq.backend.models.cosmos.ReservationCosmos;
import com.noq.backend.services.cosmos.ReservationCosmosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationsViewCosmosController {
    private final ReservationCosmosService service;

    @GetMapping
    public Mono<ResponseEntity<ReservationsViewDTO>> getReservation(@RequestParam String userId) {
        return service
                .getReservationByUserId(userId)
                .mapNotNull(reservation -> ResponseEntity.ok(toDTO(reservation)));
    }

    @ExceptionHandler(NoReservationsException.class)
    public ResponseEntity<String> handleNoReservationsException(NoReservationsException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    private ReservationsViewDTO toDTO(ReservationCosmos reservation) {
        Address address = reservation.getHost().getAddress();
        AddressDTO addressDTO = new AddressDTO(
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
