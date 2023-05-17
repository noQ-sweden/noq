package com.noq.backend.services;

import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.models.Status;
import com.noq.backend.models.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationService {

    public Reservation createReservation(Host host, String userId) {
        User user = new User(userId, "hej", true);
        Reservation reservation = new Reservation(host, user, Status.RESERVED);
        System.out.print(reservation);
        return reservation;
    }
}
