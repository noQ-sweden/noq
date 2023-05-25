package com.noq.backend.services;

import com.noq.backend.models.*;
import com.noq.backend.repository.HostRepository;
import com.noq.backend.repository.ReservationRepository;
import com.noq.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private HostRepository hostRepository;
    private UserRepository userRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository, UserRepository userRepository) {
        this.hostRepository = hostRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    public Reservation getReservationByUserId(String userId) {
        List<Reservation> reservations = reservationRepository.getAllReservations();
        Reservation reservation = reservations.stream()
                .filter(res -> res.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        System.out.print(reservation);
        return reservation;
    }

    public Reservation createReservation(CreateReservation createReservation) {
        User user = userRepository.getUserByUserId(createReservation.getUserId());
        user.setReservation(true);
        userRepository.save(user);

        Host host = hostRepository.getHostByHostId(createReservation.getHostId());

        Reservation reservation = new Reservation(host, user, Status.RESERVED);
        reservationRepository.save(reservation);
        System.out.print(reservation);
        return reservation;
    }
}
