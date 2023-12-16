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
    // FIXME to @GetMapping("/{hostId}") after removing Hardcoding of 550e8400-e29b-41d4-a716-446655440000
    public ResponseEntity<BookingsPageDTO> getBookingsPageForHost(/*@PathVariable String hostId*/) {
        log.info("Loading BookingsPageDTO");
        // TODO Remove this Hardcoding. Frontend should send correct hostId
        String hostId = "550e8400-e29b-41d4-a716-446655440000";
        // FIXME Error when Host does not exist
        BookingsPageDTO dto = toDTO(hostId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("approve")
    public ResponseEntity<BookingsPageDTO> approve(@RequestBody BookingApprovalReqBody reqBody) {
        String hostId = "550e8400-e29b-41d4-a716-446655440000";
        bookingService.decideApproval(UUID.fromString(reqBody.bookingId()), ApprovalStatus.APPROVE);
        var dto = toDTO(hostId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("deny")
    public ResponseEntity<BookingsPageDTO> deny(@RequestBody BookingApprovalReqBody reqBody) {
        String hostId = "550e8400-e29b-41d4-a716-446655440000";
        bookingService.decideApproval(UUID.fromString(reqBody.bookingId()), ApprovalStatus.DENY);
        var dto = toDTO(hostId);
        return ResponseEntity.ok(dto);
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

    private BookingsPageDTO toDTO(String hostId) {
        List<Booking> allBookingsForHost = bookingService.findBookingsForHost(UUID.fromString(hostId));
        return BookingsPageDTO.builder()
                .id(hostId)
                .approvedBookings(allBookingsForHost.stream()
                        .filter(booking -> booking.getBookingStatus() == BookingStatus.APPROVED).map(this::toDTO).toArray(BookingsPageDTO.BookingDTO[]::new))
                .disapprovedBookings(allBookingsForHost.stream().filter(booking -> booking.getBookingStatus() == BookingStatus.DENIED).map(this::toDTO).toArray(BookingsPageDTO.BookingDTO[]::new))
                .pendingBookings(allBookingsForHost.stream().filter(booking -> booking.getBookingStatus() == BookingStatus.PENDING).map(this::toDTO).toArray(BookingsPageDTO.BookingDTO[]::new))
                .build();
    }
}
