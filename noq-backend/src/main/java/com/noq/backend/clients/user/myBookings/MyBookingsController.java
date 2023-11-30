package com.noq.backend.clients.user.myBookings;

import com.noq.backend.clients.user.booking.UserBookingController;
import com.noq.backend.clients.user.booking.UserBookingPageDTO;
import com.noq.backend.models.Booking;
import com.noq.backend.models.Host;
import com.noq.backend.models.User;
import com.noq.backend.services.BookingService;
import com.noq.backend.services.HostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@RestController
@RequestMapping("/api/user/my-bookings")
@RequiredArgsConstructor
public class MyBookingsController {
    private final BookingService bookingService;
    private final HostService hostService;

    @GetMapping
    public ResponseEntity<MyBookingsDTO> getMyBookingsPage() {
        log.info("getBookingPage");
        MyBookingsDTO toDTO = buildDTO();
        return ResponseEntity.ok(toDTO);
    }

    public MyBookingsDTO buildDTO() {
        DTOBuilder dtoBuilder = new DTOBuilder();
        List<Booking> bookingsForUser = bookingService.findBookingsForUser(UUID.fromString("550e8400-e29b-41d4-a716-446655440011"));
        List<Host> hosts = hostService.allHosts();
        dtoBuilder.setBookings(bookingsForUser);
        dtoBuilder.setHosts(hosts);
        return toDTO(dtoBuilder);
    }

    private static MyBookingsDTO toDTO(DTOBuilder dtoBuilder) {
        return new MyBookingsDTO(
                "550e8400-e29b-41d4-a716-446655440011",
                dtoBuilder.getBookings().stream().map(booking -> toDTO(booking, dtoBuilder::getHostById)).toArray(MyBookingsDTO.BookingDTO[]::new)
        );
    }

    private static MyBookingsDTO.BookingDTO toDTO(Booking booking, Function<UUID, Host> getHostById) {
        return new MyBookingsDTO.BookingDTO(
                booking.getBookingId().toString(),
                booking.getBookingStatus().toString(),
                toDTO((getHostById.apply(booking.getHostId())))
        );
    }

    private static MyBookingsDTO.Host toDTO(Host host) {
        return new MyBookingsDTO.Host(
                host.getHostId().toString(),
                host.getName(),
                host.getAddress_1(),
                host.getAddress_2(),
                host.getAddressPostcode(),
                host.getCity(),
                host.getCountOfAvailablePlaces(),
                host.getTotalPlaces()
        );
    }

    @Data
    static class DTOBuilder {
        User user;
        List<Booking> bookings;
        List<Host> hosts;

        Host getHostById(UUID hostId) {
            return hosts.stream().filter(host -> host.getHostId().equals(hostId)).findFirst()
                    .orElseThrow(() -> new RuntimeException("Host not found"));
        }
    }

}
