package com.noq.backend.controllers.host.bookings;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/host/bookings")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class HostBookingsPageController {
/*    private final ReservationService reservationService;

    @GetMapping()
    public ResponseEntity<HostBookingsPageDTO> bookingsPage(@RequestHeader("Authorization") String token) {
        log.info("bookingsPage");
        log.info("token: {}", token);
        HostBookingsPageDTO hostRequestsPageDTO = toHostRequestsViewDTO(Optional.empty());
        return ResponseEntity.ok(hostRequestsPageDTO);
    }

    private HostBookingsPageDTO toHostRequestsViewDTO(Optional<Function<DTOBuilder, DTOBuilder>> additionalProcessing) {
        return Stream.of(new DTOBuilder())
                .map(additionalProcessing.orElseGet(Function::identity))
                .map(reservationService.updateDTOBuilderWithReservations(DTOBuilder::setReservations))
                .map(HostBookingsPageController::toDTO)
                .findFirst().get();
    }

    private static HostBookingsPageDTO toDTO(DTOBuilder DTOBuilder) {
        return new HostBookingsPageDTO(
                "DTOBuilder.getHost().getHostId()",
                DTOBuilder.getApprovedReservations().stream().map(HostBookingsPageController::toDTO).toArray(HostBookingsPageDTO.Reservation[]::new)
        );
    }

    private static HostBookingsPageDTO.Reservation toDTO(Reservation reservation) {
        return new HostBookingsPageDTO.Reservation(
                reservation.getReservationId(),
                reservation.getUser().getFirstName(),
                1,
                reservation.getStatus()
        );
    }

    @Data
    @NoArgsConstructor
    private static class DTOBuilder {
        private Host host;
        private List<Reservation> reservations = new ArrayList<>();

        private List<Reservation> getApprovedReservations () {
            return reservations.stream().filter(reservation -> reservation.getStatus().equals(Reservation.Status.APPROVED)).collect(Collectors.toList());

        }

    }*/
}
