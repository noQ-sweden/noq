package com.noq.backend.services;

import com.noq.backend.exceptions.BookingException;
import com.noq.backend.models.ApprovalStatus;
import com.noq.backend.models.Booking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public interface BookingService {

    Booking createBooking(UUID hostId, UUID userId, LocalDateTime startDateTime);

    void decideApproval(UUID bookingId, ApprovalStatus approvalStatusToBeUpdated) throws BookingException;

    void cancelBooking(UUID bookingId);

    List<Booking> findBookingsForHost(UUID hostId);

    List<Booking> findBookingsForUser(UUID userId);
}
