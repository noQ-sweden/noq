package com.noq.backend.clients.host.bookings;

import com.noq.backend.clients.host.bookings.reqBody.BookingApprovalReqBody;
import com.noq.backend.models.*;
import com.noq.backend.services.BookingService;
import com.noq.backend.services.HostService;
import com.noq.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/host/requests")
@RequiredArgsConstructor
public class BookingsPageController {
    private final BookingService bookingService;
    private final HostService hostService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<BookingsPageDTO> getBookingsPageForHost() {
        log.info("getBookingsPageForHost - Feature temporarily disabled");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new BookingsPageDTO(List.of()));
    }

    @PostMapping("approve")
    public ResponseEntity<BookingsPageDTO> approve(@RequestBody BookingApprovalReqBody reqBody) {
        log.info("approve - Feature temporarily disabled");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new BookingsPageDTO(List.of()));
    }

    @PostMapping("deny")
    public ResponseEntity<BookingsPageDTO> deny(@RequestBody BookingApprovalReqBody reqBody) {
        log.info("deny - Feature temporarily disabled");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new BookingsPageDTO(List.of()));
    }

    private BookingsPageDTO.BookingDTO toDTO(Booking booking) {
        // FIXME Add UNOKOD property on the Booking Object in addition to the Id
        User userById = userService.getUserById(booking.getUserId());
        var unokod = userById.getUnokod();
        if (unokod == null) {
            log.warn("UNOKOD not found for %s".formatted(booking.getUserId()));
        }
        return BookingsPageDTO.BookingDTO.builder()
                .id(booking.getBookingId().toString())
                .userId(userById.getUserId().toString())
                .unoCode(unokod)
                .name(userById.getFirstName() + " " + userById.getLastName())
                // FIXME Implementation for Queuing of Bookings
                .queuingPlace(0)
                .status(booking.getBookingStatus().name())
                .build();
    }
}
