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
        // Assume that User Always Exists FIXME User Administration UI and Backend & errorHandling in case User Does not Exist
        var user = userRepository.findById(userId).orElseThrow();
        log.info("Creating a Booking for user {}", user.getUserId());

        // Check if host exists and is available
        var availableHost = hostRepository.findById(hostId)
                // Check if Vacant Places exist on the Host
                .filter(host -> host.getCountOfAvailablePlaces() > 0)
                .orElseThrow(() -> new HostNotFoundException(hostId.toString()));

        // Add to Queue of Pending Requests (this does not decrement available count on Host)
        return bookingRepository.save(Booking.builder()
                .userId(user.getUserId())
                .hostId(availableHost.getHostId())
                .bookingStatus(BookingStatus.PENDING)
                .approvalStatus(ApprovalStatus.PENDING)
                .startDateTime(startDateTime)
                // FIXME What is Correct Duration of booking for someone to stay in? defaulting to 8 hours
                .endDateTime(startDateTime.plusHours(HOURS_UNTIL_BOOKING_ENDS))
                .caseManagerName("Handläggare Namn")
                .caseManagerEmail("handlaggare@noq.se")
                .build());
    }

    @Override
    public void decideApproval(UUID bookingId, ApprovalStatus approvalStatusToBeUpdated) {
        bookingRepository.findById(bookingId)
                .map(booking -> {
                    var currentApprovalStatus = booking.getApprovalStatus();
                    if (currentApprovalStatus == ApprovalStatus.PENDING) {
                        switch (approvalStatusToBeUpdated) {
                            case APPROVE -> booking = booking.toBuilder()
                                    .bookingStatus(BookingStatus.APPROVED)
                                    .approvalStatus(approvalStatusToBeUpdated)
                                    .build();
                            case DENY -> booking = booking.toBuilder()
                                    .bookingStatus(BookingStatus.DENIED)
                                    .approvalStatus(approvalStatusToBeUpdated)
                                    .build();
                            case PENDING -> log.info("No change in Approval Status");
                        }
                    } else {
                        throw new IllegalStateException("Cannot change Approval status once APPROVED or DENIED. Create a new Reservation.");
                    }
                    return bookingRepository.save(booking);
                })
                .orElseThrow(() -> new RuntimeException("Booking Not Found"));
    }

    @Override
    public void cancelBooking(UUID bookingId) {
        throw new RuntimeException("NOT IMPLEMENTED cancelBooking");
    }

    @Override
    public List<Booking> findBookingsForHost(UUID hostId) {
        List<Booking> bookingsByHostId = bookingRepository.findAllByHostId(hostId);
        log.info("Found {} bookings for HostId {}", bookingsByHostId.size(), hostId);
        return bookingsByHostId;
    }

    @Override
    public List<Booking> findBookingsForUser(UUID userId) {
        List<Booking> bookingsByUserId = bookingRepository.findAllByUserId(userId);
        log.info("Found {} bookings for UserId {}", bookingsByUserId.size(), userId);
        return bookingsByUserId;
    }
}
