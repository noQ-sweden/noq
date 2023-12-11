package com.noq.backend.clients.host.requests;

import com.noq.backend.clients.user.myBookings.MyBookingsController;
import com.noq.backend.clients.user.myBookings.MyBookingsDTO;
import com.noq.backend.models.Booking;
import com.noq.backend.models.Host;
import com.noq.backend.services.BookingService;
import com.noq.backend.services.BookingServiceImpl;
import com.noq.backend.services.HostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/host/requests")
@RequiredArgsConstructor
public class RequestsPageController {
    private final BookingService bookingService;
    private final HostService hostService;

    @GetMapping("{hostId}")
    public ResponseEntity<RequestsPageDTO> getPage(@PathVariable String hostId) {
        log.info("RequestsPageDTO");
        hostId = "550e8400-e29b-41d4-a716-446655440000";
        RequestsPageDTO dto = buildDTO(hostId);
        return ResponseEntity.ok(dto);
    }

    private RequestsPageDTO buildDTO(String hostId) {
        DTOBuilder dtoBuilder = new DTOBuilder();
        Host host = hostService.findHostById(UUID.fromString(hostId));
        List<Booking> approvedBookings = bookingService.findBookingsForHost(UUID.fromString(hostId));
        List<Booking> disApprovedBookings = bookingService.findBookingsForHost(UUID.fromString(hostId));
        List<Booking> pendingBookings = bookingService.findBookingsForHost(UUID.fromString(hostId));

        dtoBuilder.setHost(host);
        dtoBuilder.setApprovedReservations(approvedBookings);
        dtoBuilder.setDisapprovedReservations(disApprovedBookings);
        dtoBuilder.setPendingReservations(pendingBookings);
        return toDTO(dtoBuilder);
    }

    private static RequestsPageDTO toDTO(DTOBuilder dtoBuilder) {
        return new RequestsPageDTO(
                dtoBuilder.getHost().getHostId().toString(),
                dtoBuilder.getApprovedReservations().stream().map(RequestsPageController::toDTO).toArray(RequestsPageDTO.Reservation[]::new),
                dtoBuilder.getDisapprovedReservations().stream().map(RequestsPageController::toDTO).toArray(RequestsPageDTO.Reservation[]::new),
                dtoBuilder.getPendingReservations().stream().map(RequestsPageController::toDTO).toArray(RequestsPageDTO.Reservation[]::new)
        );
    }

    private static RequestsPageDTO.Reservation toDTO(Booking booking) {
        return new RequestsPageDTO.Reservation(
                booking.getBookingId().toString(),
                booking.getUserId().toString(),
                "booking.getName()",
                "booking.getUnoCode()",
                1,
                "booking.getStatus()"
        );
    }

    @Data
    private static class DTOBuilder {
        Host host;
        List<Booking> approvedReservations = new ArrayList<>();
        List<Booking> disapprovedReservations = new ArrayList<>();
        List<Booking> pendingReservations = new ArrayList<>();
    }
}
