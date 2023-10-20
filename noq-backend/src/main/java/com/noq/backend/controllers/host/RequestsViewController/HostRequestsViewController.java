package com.noq.backend.controllers.host.RequestsViewController;

import com.noq.backend.controllers.host.RequestsViewController.reqBodys.UpdateReservationStatusField;
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
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/api/host/requests")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class HostRequestsViewController {
    private final HostService hostCosmosService;
    private final ReservationService reservationService;

    @GetMapping()
    public Mono<HostRequestsViewDTO> requestsViewModel() {
        log.info("requestsViewModel");
        return toHostRequestsViewDTO(Optional.empty());
    }

    @PutMapping("update-reservation-status-field")
    public Mono<HostRequestsViewDTO> updateReservationStatusField(@RequestBody UpdateReservationStatusField reqBody) {
        log.info("requestsViewModel");
        log.info("reqBody: {}", reqBody);
        return toHostRequestsViewDTO(Optional.of(dtoBuilder -> reservationService.updateReservationField(reqBody.reservationId(), reqBody.newValue(), reqBody.updateChangeType())
                .thenReturn(dtoBuilder)));
    }

    private Mono<HostRequestsViewDTO> toHostRequestsViewDTO(Optional<Function<DTOBuilder, Mono<DTOBuilder>>> additionalProcessing) {
        return Mono.just("")
                .map(o -> new DTOBuilder())
                .flatMap(additionalProcessing.orElse(Mono::just))
                .flatMap(reservationService.updateDTOBuilderWithReservations(DTOBuilder::setReservations))
                .map(HostRequestsViewController::toDTO);
    }

    private static HostRequestsViewDTO toDTO(DTOBuilder DTOBuilder) {
        return new HostRequestsViewDTO(
                "DTOBuilder.getHost().getHostId()",
                DTOBuilder.getReservations().stream().map(HostRequestsViewController::toDTO).toArray(HostRequestsViewDTO.Reservation[]::new)
        );
    }

    private static HostRequestsViewDTO.Reservation toDTO(Reservation reservation) {
        return new HostRequestsViewDTO.Reservation(
                reservation.getReservationId(),
                reservation.getUser().getName(),
                1,
                reservation.getStatus()
        );
    }

    @Data
    @NoArgsConstructor
    private static class DTOBuilder {
        private Host host;
        private List<Reservation> reservations = new ArrayList<>();

    }

}
