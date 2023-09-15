package com.noq.backend.controllers.cosmos;

import com.noq.backend.DTO.cosmos.RequestsViewCosmosDTO;
import com.noq.backend.models.Status;
import com.noq.backend.models.cosmos.ReservationCosmos;
import com.noq.backend.services.cosmos.ReservationCosmosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/requests")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RequestsViewCosmosController {
    private final ReservationCosmosService service;

    @GetMapping("/{hostId}")
    public Flux<RequestsViewCosmosDTO> getReservationsByHostId(@PathVariable String hostId) {
        return service
                .getReservationsByHostId(hostId)
                .map(this::toDTO);
    }

    @GetMapping("/{hostId}/status")
    public Flux<RequestsViewCosmosDTO> getReservationsByHostIdAndStatus(
            @PathVariable String hostId,
            @RequestParam String status) {
        Flux<RequestsViewCosmosDTO> response;
        switch (status.toUpperCase()) {
            case "PENDING" -> response = service
                    .getReservationsByHostIdStatusPending(hostId)
                    .map(this::toDTO);
            case "RESERVED" -> response = service
                    .getReservationsByHostIdStatusReserved(hostId)
                    .map(this::toDTO);
            default -> {
                return Flux.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status"));
            }
        }
        return response;
    }

    @PutMapping("/{hostId}")
    public Flux<ResponseEntity<String>> approveReservations(
            @PathVariable("hostId") String hostId,
            @RequestBody List<String> reservationsId
    ) {
        return service
                .approveReservations(reservationsId)
                .map(reservation -> {
                    RequestsViewCosmosDTO dto = toDTO(reservation);
                    return ResponseEntity.ok("Approved reservation for: \n" + dto);
                });
    }

    private RequestsViewCosmosDTO toDTO(ReservationCosmos reservation) {
        RequestsViewCosmosDTO.User userDTO = new RequestsViewCosmosDTO.User(
                reservation.getUser().getId(),
                reservation.getUser().getName()
        );
        RequestsViewCosmosDTO.Reservation reservationDTO = new RequestsViewCosmosDTO.Reservation(
                reservation.getReservationId(),
                reservation.getStatus(),
                userDTO
        );
        return new RequestsViewCosmosDTO(reservationDTO);
    }

}
