package com.noq.backend.clients.host.booking;

import com.noq.backend.models.ApprovalStatus;
import com.noq.backend.models.Booking;
import com.noq.backend.services.BookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/host/bookings")
@AllArgsConstructor
public class HostBookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{hostId}")
    public HostBookingsDTO findBookingsForHost(@PathVariable UUID hostId) {
        log.info("Finding Bookings for HostId: {}", hostId);
        // TODO Create a BookingDTO for convenience & not mixing View (DTO) with Domain Models
        return new HostBookingsDTO(bookingService.findBookingsForHost(hostId));
    }

    @PostMapping("/{hostId}/approve")
    public ResponseEntity<String> approve(@PathVariable UUID hostId, @RequestBody BookingApprovalDTO bookingApprovalDTO) {
        try {
            bookingService.decideApproval(UUID.fromString(bookingApprovalDTO.getBookingId()), ApprovalStatus.valueOf(bookingApprovalDTO.getApprovalStatus()));
            return ResponseEntity.ok("Booking approval status for %s updated successfully as: %s"
                    .formatted(bookingApprovalDTO.getBookingId(), bookingApprovalDTO.getApprovalStatus()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/host/{hostId}/cancel/{bookingId}")
    public ResponseEntity<String> cancel(@PathVariable UUID hostId, @PathVariable UUID bookingId) {
        try {
            // TODO Don't Cancel Booking if Does not belong to this Host
            bookingService.cancelBooking(bookingId);
            return ResponseEntity.ok("Booking approval status updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

