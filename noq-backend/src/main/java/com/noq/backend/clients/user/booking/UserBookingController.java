package com.noq.backend.clients.user.booking;

import com.noq.backend.clients.user.booking.reqbody.UserCreateBookingRequestDTO;
import com.noq.backend.models.Booking;
import com.noq.backend.models.Host;
import com.noq.backend.services.BookingService;
import com.noq.backend.services.HostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/user/booking")
@RequiredArgsConstructor
public class UserBookingController {

    private final BookingService bookingService;
    private final HostService hostService;

    String userId = "550e8400-e29b-41d4-a716-446655440010";

    @GetMapping("{hostId}")
    public ResponseEntity<UserBookingPageDTO> getBookingPage(@PathVariable UUID hostId) {
        log.info("getBookingPage - Feature temporarily disabled");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new UserBookingPageDTO(
                        "550e8400-e29b-41d4-a716-446655440011",
                        hostId.toString(),
                        "Feature Temporarily Disabled",
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A",
                        0,
                        0));
    }

    @PutMapping("create-booking")
    public ResponseEntity<String> createBooking(@RequestBody UserCreateBookingRequestDTO reqBody) {
        log.info("createBooking - Feature temporarily disabled");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Shelter booking feature is temporarily disabled for the demo and soft launch.");
    }

    @GetMapping("/user/{userId}")
    public List<Booking> findBookingsForUser(@PathVariable UUID userId) {
        log.info("findBookingsForUser - Feature temporarily disabled");
        return List.of();
    }

    @PostMapping("/user/{userId}/cancel/{bookingId}")
    public ResponseEntity<String> cancel(@PathVariable UUID userId, @PathVariable UUID bookingId) {
        log.info("cancel - Feature temporarily disabled");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Shelter booking feature is temporarily disabled for the demo and soft launch.");
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
                dtoBuilder.getHost().getTotalPlaces());
    }

    @Data
    static class DTOBuilder {
        // User user;
        Host host;

        // public DTOBuilder(User user) {
        // this.user = user;
        // }
    }
}
