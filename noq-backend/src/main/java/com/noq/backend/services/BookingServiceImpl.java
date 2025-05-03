package com.noq.backend.services;

import com.noq.backend.exceptions.BookingException;
import com.noq.backend.exceptions.HostNotFoundException;
import com.noq.backend.models.*;
import com.noq.backend.repositories.BookingRepository;
import com.noq.backend.repositories.HostRepository;
import com.noq.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    public static final int HOURS_UNTIL_BOOKING_ENDS = 8;

    @Autowired
    private final HostRepository hostRepository;

    @Autowired
    private final BookingRepository bookingRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public Booking createBooking(UUID hostId, UUID userId, LocalDateTime startDateTime) {
        log.info("createBooking - Feature temporarily disabled");
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                "Shelter booking feature is temporarily disabled for the demo and soft launch.");
    }

    @Override
    public void decideApproval(UUID bookingId, ApprovalStatus approvalStatusToBeUpdated) {
        log.info("decideApproval - Feature temporarily disabled");
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                "Shelter booking feature is temporarily disabled for the demo and soft launch.");
    }

    @Override
    public void cancelBooking(UUID bookingId) {
        log.info("cancelBooking - Feature temporarily disabled");
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                "Shelter booking feature is temporarily disabled for the demo and soft launch.");
    }

    @Override
    public List<Booking> findBookingsForHost(UUID hostId) {
        log.info("findBookingsForHost - Feature temporarily disabled");
        return List.of();
    }

    @Override
    public List<Booking> findBookingsForUser(UUID userId) {
        log.info("findBookingsForUser - Feature temporarily disabled");
        return List.of();
    }
}
