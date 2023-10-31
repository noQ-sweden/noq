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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/host/requests")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class HostRequestsViewController {
    private final HostService hostCosmosService;
    private final ReservationService reservationService;

    @GetMapping()
    public ResponseEntity<HostRequestsViewDTO> requestsViewModel(@RequestHeader("Authorization") String token) {
        log.info("requestsViewModel");
        log.info("token: {}", token);
        HostRequestsViewDTO hostRequestsViewDTO = toHostRequestsViewDTO(Optional.empty());
        return ResponseEntity.ok(hostRequestsViewDTO);
    }

    @PutMapping("update-reservation-status-field")
    public ResponseEntity<HostRequestsViewDTO> updateReservationStatusField(@RequestHeader("Authorization") String token,
                                                                            @RequestBody UpdateReservationStatusField reqBody) {
        log.info("requestsViewModel");
        log.info("reqBody: {}", reqBody);
        var hostRequestsViewDTO = toHostRequestsViewDTO(
                Optional.of(dtoBuilder -> {
                    reservationService.updateReservationField(reqBody.reservationId(), reqBody.newValue(), reqBody.updateChangeType());
                    return dtoBuilder;
                }));
        return ResponseEntity.ok(hostRequestsViewDTO);
    }

    private HostRequestsViewDTO toHostRequestsViewDTO(Optional<Function<DTOBuilder, DTOBuilder>> additionalProcessing) {
        return Stream.of(new DTOBuilder())
                .map(additionalProcessing.orElseGet(Function::identity))
                .map(reservationService.updateDTOBuilderWithReservations(DTOBuilder::setReservations))
                .map(HostRequestsViewController::toDTO)
                .findFirst().get();
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
