package com.noq.backend.clients.host.bookings;

import com.noq.backend.clients.host.bookings.reqBody.BookingApprovalDTO;
import com.noq.backend.models.ApprovalStatus;
import com.noq.backend.models.Booking;
import com.noq.backend.services.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/host/bookings")
@RequiredArgsConstructor
public class HostBookingsController {
    private final BookingService bookingService;

    @GetMapping("/{hostId}")
    public ResponseEntity<HostBookingsDTO> getBookingsForHost(@PathVariable UUID hostId) {
        List<Booking> bookingsForHost = bookingService.findBookingsForHost(hostId);
        List<HostBookingsDTO.BookingDTO> listofBookingsForHost = bookingsForHost.stream().map(booking -> new HostBookingsDTO.BookingDTO(
                booking.getBookingId().toString(),
                booking.getBookingStatus().name()
        ))
                .toList();
        return ResponseEntity.ok(new HostBookingsDTO(hostId.toString(), listofBookingsForHost.toArray(HostBookingsDTO.BookingDTO[]::new)));
    }

    @PostMapping("/{hostId}/handle-status")
    public ResponseEntity<String> handleStatus(@PathVariable UUID hostId, @RequestBody BookingApprovalDTO bookingApprovalDTO) {
        try {
            // FIXME Validate that hostId has the matching bookingId inside BookingApprovalDTO
            bookingService.decideApproval(UUID.fromString(bookingApprovalDTO.getBookingId()), ApprovalStatus.valueOf(bookingApprovalDTO.getApprovalStatus()));
            return ResponseEntity.ok("Booking approval status for %s updated successfully as: %s"
                    .formatted(bookingApprovalDTO.getBookingId(), bookingApprovalDTO.getApprovalStatus()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
