package com.noq.backend.clients.user.booking;

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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/user/booking")
@AllArgsConstructor
public class UserBookingController {

    @Autowired
    private BookingService bookingService;

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBooking(@RequestBody CreateBookingRequestDto createBookingRequestDto) {
        try {
            var booking = bookingService.createBooking(createBookingRequestDto.getHostId(), createBookingRequestDto.getUserId(), createBookingRequestDto.getStartDateTime());
            return ResponseEntity.ok("Booking created with ID: " + booking.getBookingId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public List<Booking> findBookingsForUser(@PathVariable UUID userId) {
        log.info("Finding Bookings for UserId: {}", userId);
        return bookingService.findBookingsForUser(userId);
    }

    @PostMapping("/user/{userId}/cancel/{bookingId}")
    public ResponseEntity<String> cancel(@PathVariable UUID userId, @PathVariable UUID bookingId) {
        try {
            // TODO Don't Cancel Booking if Does not belong to this User
            bookingService.cancelBooking(bookingId);
            return ResponseEntity.ok("Booking approval status updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

