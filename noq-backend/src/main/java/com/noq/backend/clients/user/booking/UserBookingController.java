package com.noq.backend.clients.user.booking;

import com.noq.backend.models.Booking;
import com.noq.backend.models.Host;
import com.noq.backend.services.BookingService;
import com.noq.backend.services.HostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    private HostService hostService;

    @GetMapping("{hostId}")
    public ResponseEntity<UserBookingPageDTO> getBookingPage(@PathVariable UUID hostId) {
        log.info("getBookingPage");
        log.info("hostId: {}", hostId);
        UserBookingPageDTO toDTO = buildDTO(hostId);
        return ResponseEntity.ok(toDTO);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBooking(@RequestBody UserCreateBookingRequestDto userCreateBookingRequestDto) {
        log.info("createBooking");
        log.info("createBookingRequestDto: {}", userCreateBookingRequestDto);
        try {
            var booking = bookingService.createBooking(userCreateBookingRequestDto.hostId(), userCreateBookingRequestDto.userId(), LocalDateTime.now());
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

    public UserBookingPageDTO buildDTO(UUID hostId) {
        DTOBuilder dtoBuilder = new DTOBuilder();
        var host = hostService.findHostById(hostId);
        dtoBuilder.setHost(host);
        return toDTO(dtoBuilder);
    }

    UserBookingPageDTO toDTO(DTOBuilder dtoBuilder) {
        return new UserBookingPageDTO(
                "550e8400-e29b-41d4-a716-446655440011",
                dtoBuilder.getHost().getHostId().toString(),
                dtoBuilder.getHost().getName(),
                dtoBuilder.getHost().getAddress_1(),
                dtoBuilder.getHost().getAddress_2(),
                dtoBuilder.getHost().getAddressPostcode(),
                dtoBuilder.getHost().getCity(),
                dtoBuilder.getHost().getCountOfAvailablePlaces(),
                dtoBuilder.getHost().getTotalPlaces()
        );
    }

    @Data
    static class DTOBuilder {
//        User user;
        Host host;

//        public DTOBuilder(User user) {
//            this.user = user;
//        }
    }
}

