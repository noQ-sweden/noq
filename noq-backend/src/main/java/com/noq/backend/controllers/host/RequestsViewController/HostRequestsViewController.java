package com.noq.backend.controllers.host.RequestsViewController;

import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.services.HostService;
import com.noq.backend.services.ReservationService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/host/requests")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class HostRequestsViewController {
    private final HostService hostCosmosService;
    private final ReservationService reservationService;

    @GetMapping()
    public Mono<RequestsViewDTO> requestsViewModel() {
        log.info("requestsViewModel");
        return Mono.just("")
                .map(o -> new DTOBuilder())
                .flatMap(reservationService.updateDTOBuilderWithReservations(DTOBuilder::setReservations))
                .map(HostRequestsViewController::toDTO);
    }

    private static RequestsViewDTO toDTO(DTOBuilder DTOBuilder) {
        return new RequestsViewDTO(
                "DTOBuilder.getHost().getHostId()",
                DTOBuilder.getReservations().stream().map(HostRequestsViewController::toDTO).toArray(RequestsViewDTO.Request[]::new)
        );
    }

    private static RequestsViewDTO.Request toDTO(Reservation reservation) {
        return new RequestsViewDTO.Request(
                reservation.getReservationId(),
                reservation.getUser().getName(),
                1,
                Reservation.Status.PENDING
        );
    }

    @Data
    @NoArgsConstructor
    private static class DTOBuilder {
        private Host host;
        private List<Reservation> reservations = new ArrayList<>();

    }

}
