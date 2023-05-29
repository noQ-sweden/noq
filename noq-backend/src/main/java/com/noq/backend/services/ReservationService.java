package com.noq.backend.services;

import com.noq.backend.models.*;
import com.noq.backend.repository.HostRepository;
import com.noq.backend.repository.ReservationRepository;
import com.noq.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return reservation;
    }

    public Reservation createReservation(CreateReservation createReservation) {
        User user = userRepository.getUserByUserId(createReservation.getUserId());
        user.setReservation(true);
        userRepository.save(user);

        Host host = hostRepository.getHostByHostId(createReservation.getHostId());

        Reservation reservation = new Reservation(host, user, Status.PENDING);
        reservationRepository.save(reservation);
        return reservation;
    }

    // returns empty array...??
    public List<Reservation> getReservationsByHostId(String hostId) {
        List<Reservation> reservations = reservationRepository.getAllReservations().stream()
                .filter(res -> res.getHost().getHostId().equals(hostId))
                .collect(Collectors.toList());
        System.out.print(reservations);
        return reservations;
    }
}
