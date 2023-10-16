package com.noq.backend.controllers.host.RequestsViewController;

import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.services.HostService;
import com.noq.backend.services.ReservationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/host/requests")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class RequestsViewController {
    private final HostService hostCosmosService;
    private final ReservationService reservationService;

    @GetMapping("{hostId}")
    public Mono<RequestsViewDTO> requestsViewModel(@PathVariable String hostId) {
        return hostCosmosService.findByHostId(hostId)
                .map(DTOBuilder::new)
                .flatMap(reservationService.updateDTOBuilderWithReservations(DTOBuilder::getHost, DTOBuilder::setReservations))
                .map(RequestsViewController::toDTO);
    }

    private static RequestsViewDTO toDTO(DTOBuilder DTOBuilder) {
        return new RequestsViewDTO(
                DTOBuilder.getHost().getHostId(),
                DTOBuilder.getReservations().stream().map(RequestsViewController::toDTO).toArray(RequestsViewDTO.Request[]::new)
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
    private static class DTOBuilder {
        private Host host;
        private List<Reservation> reservations = new ArrayList<>();

        public DTOBuilder(Host host) {
            this.host = host;
        }
    }

}
