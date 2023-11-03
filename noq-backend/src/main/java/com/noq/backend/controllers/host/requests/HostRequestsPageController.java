package com.noq.backend.controllers.host.requests;

import com.noq.backend.controllers.host.requests.reqBodys.HostUpdateReservationStatusField;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
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
public class HostRequestsPageController {
    private final ReservationService reservationService;

    @GetMapping()
    public ResponseEntity<HostRequestsPageDTO> requestsPage(@RequestHeader("Authorization") String token) {
        log.info("requestsPage");
        log.info("token: {}", token);
        HostRequestsPageDTO hostRequestsPageDTO = toHostRequestsViewDTO(Optional.empty());
        return ResponseEntity.ok(hostRequestsPageDTO);
    }

    @PutMapping("update-reservation-status-field")
    public ResponseEntity<HostRequestsPageDTO> updateReservationStatusField(@RequestHeader("Authorization") String token,
                                                                            @RequestBody HostUpdateReservationStatusField reqBody) {
        log.info("requestsViewModel");
        log.info("reqBody: {}", reqBody);
        var hostRequestsViewDTO = toHostRequestsViewDTO(
                Optional.of(dtoBuilder -> {
                    reservationService.updateReservationField(reqBody.reservationId(), reqBody.newValue(), reqBody.updateChangeType());
                    return dtoBuilder;
                }));
        return ResponseEntity.ok(hostRequestsViewDTO);
    }

    private HostRequestsPageDTO toHostRequestsViewDTO(Optional<Function<DTOBuilder, DTOBuilder>> additionalProcessing) {
        return Stream.of(new DTOBuilder())
                .map(additionalProcessing.orElseGet(Function::identity))
                .map(reservationService.updateDTOBuilderWithReservations(DTOBuilder::setReservations))
                .map(HostRequestsPageController::toDTO)
                .findFirst().get();
    }

    private static HostRequestsPageDTO toDTO(DTOBuilder DTOBuilder) {
        return new HostRequestsPageDTO(
                "DTOBuilder.getHost().getHostId()",
                DTOBuilder.getReservations().stream().map(HostRequestsPageController::toDTO).toArray(HostRequestsPageDTO.Reservation[]::new)
        );
    }

    private static HostRequestsPageDTO.Reservation toDTO(Reservation reservation) {
        return new HostRequestsPageDTO.Reservation(
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
