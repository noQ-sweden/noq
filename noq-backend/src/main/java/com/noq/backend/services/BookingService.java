package com.noq.backend.services;

import com.noq.backend.models.ApprovalStatus;
import com.noq.backend.models.Booking;
import com.noq.backend.models.BookingStatus;
import com.noq.backend.repositories.BookingRepository;
import com.noq.backend.repositories.HostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService {

    private final HostRepository hostRepository;
    private final BookingRepository bookingRepository;

    public Booking createBooking(UUID hostId, UUID userId, LocalDateTime startDateTime) {
        // Assume that User Always Exists FIXME User Administration UI and Backend & errorHandling in case User Does not Exist
        // Check if host exists
        // Check if Vacant Places exist on the Host
        // Add to Queue of Pending Requests (this does not decrement available count on Host)
        // All Pending Requests see the same details
        return bookingRepository.save(Booking.builder()
                .bookingStatus(BookingStatus.PENDING)
                .approvalStatus(ApprovalStatus.PENDING)
                .startDateTime(startDateTime)
                // FIXME Correct Duration of booking for someone to stay in, defaulting to 8 hours
                .endDateTime(startDateTime.plusHours(8))
                .caseManagerName("Handläggare Namn")
                .caseManagerEmail("handlaggare@noq.se")
                .build());
    }

    public void decideApproval(UUID bookingId, ApprovalStatus approvalStatusToBeUpdated) {
        bookingRepository.findById(bookingId)
                .map(booking -> {
                    var currentApprovalStatus = booking.getApprovalStatus();
                    if (currentApprovalStatus == ApprovalStatus.PENDING) {
                        switch (approvalStatusToBeUpdated) {
                            case ACCEPTED -> booking = booking.toBuilder()
                                    .bookingStatus(BookingStatus.APPROVED)
                                    .approvalStatus(approvalStatusToBeUpdated)
                                    .build();
                            case DENIED -> booking = booking.toBuilder()
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
}
