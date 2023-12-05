package com.noq.backend.clients.host.myLocations;

import com.noq.backend.clients.host.myLocations.reqBody.BookingApprovalDTO;
import com.noq.backend.models.ApprovalStatus;
import com.noq.backend.models.Host;
import com.noq.backend.services.BookingService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/host/bookings")
@RequiredArgsConstructor
public class MyLocationsController {
    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<MyLocationsDTO> getPage() {
        return ResponseEntity.ok(toDTO(new DTOBuilder()));
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

    MyLocationsDTO toDTO(DTOBuilder dtoBuilder) {
        return null;
    }

    @Data
    static class DTOBuilder {
        Host host;
    }
}

