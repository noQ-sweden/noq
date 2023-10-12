package com.noq.backend.controllers;

import com.noq.backend.DTO.RequestsViewDTO;
import com.noq.backend.models.Status;
import com.noq.backend.models.cosmos.HostCosmos;
import com.noq.backend.models.cosmos.ReservationCosmos;
import com.noq.backend.services.cosmos.HostCosmosService;
import com.noq.backend.services.cosmos.ReservationCosmosService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class RequestsViewController {
    private final HostCosmosService hostCosmosService;
    private final ReservationCosmosService reservationCosmosService;

    @GetMapping("{hostId}")
    public Mono<RequestsViewDTO> requestsViewModel(@PathVariable String hostId) {
        return hostCosmosService.findByHostId(hostId)
                .map(Param::new)
                .flatMap(reservationCosmosService.updateParamWithReservations(Param::getHost, Param::setReservations))
                .map(RequestsViewController::toDTO);
    }

/*    @GetMapping("/get-pending/{hostId}")
    public List<RequestsViewDTO> getPendingByHostId(@PathVariable String hostId) {
        return reservationService.getReservationsByHostIdStatusPending(hostId)
                .stream()
                .map(RequestsViewController::toDTO)
                .collect(Collectors.toList());
    }*/

//    @PutMapping("/approve-reservations/{hostId}")
//    public ResponseEntity<String> approveReservations(@RequestBody List<String> reservationsId, @PathVariable String hostId) {
//        reservationService.approveReservations(reservationsId, hostId);
//        String responseMessage = " approved reservations";
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
//    }
//
//    @PutMapping("/reject-reservations/{hostId}")
//    public ResponseEntity<String> rejectReservations(@RequestBody List<String> reservationsId, @PathVariable String hostId) {
//        reservationService.rejectReservations(reservationsId, hostId);
//        String responseMessage = " rejected reservations";
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
//    }

   /* @GetMapping("/get-approved/{hostId}")
    public List<RequestsViewDTO> getApprovedByHostId(@PathVariable String hostId) {
        return reservationService.getReservationsByHostIdStatusReserved(hostId)
                .stream()
                .map(RequestsViewController::toDTO)
                .collect(Collectors.toList());
    }*/


//    private static RequestsViewDTO toDTO(List<Reservation> reservation) {
//        List<RequestsViewDTO.Reservation> reservations = reservation.stream()
//                .map(reservation1 -> {
//                    RequestsViewDTO.User user = new RequestsViewDTO.User(
//                            reservation1.getUser().getId(),
//                            reservation1.getUser().getName());
//                    RequestsViewDTO.Reservation res = new RequestsViewDTO.Reservation(
//                            reservation1.getReservationId(),
//                            reservation1.getStatus(),
//                            user);
//                    return res;
//                }).collect(Collectors.toList());
//
//
//        return new RequestsViewDTO()
//    }

    private static RequestsViewDTO toDTO(Param param) {
        return new RequestsViewDTO(
                param.getHost().getHostId(),
                param.getReservations().stream()
                        .map(RequestsViewController::toDTO)
                        .toArray(RequestsViewDTO.Request[]::new)
        );
    }

    private static RequestsViewDTO.Request toDTO(ReservationCosmos reservation) {
        return new RequestsViewDTO.Request(
                reservation.getReservationId(),
                reservation.getUser().getName(),
                1,
                Status.PENDING
        );
    }

    @Data
    private static class Param {
        private HostCosmos host;
        private List<ReservationCosmos> reservations = new ArrayList<>();

        public Param(HostCosmos host) {
            this.host = host;
        }
    }

}
