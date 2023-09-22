package com.noq.backend.services;

import com.noq.backend.exeptions.ReservationNotFoundException;
import com.noq.backend.models.*;
import com.noq.backend.repository.HostRepository;
import com.noq.backend.repository.ReservationRepository;
import com.noq.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if (reservations.isEmpty()) {
            throw new ReservationNotFoundException("No reservations found for user " + userId);
        }
        Reservation reservation = reservations.stream()
                .filter(res -> res.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);

        return reservation;
    }

    public Reservation createReservation(CreateReservation createReservation) {
        User user = userRepository.getUserByUserId(createReservation.getUserId());
        Host host = hostRepository.getHostByHostId(createReservation.getHostId());

        Bed reservedBed = host.getBeds().stream()
                .filter(bed -> bed.getId().equals(createReservation.getBedId()))
                .findFirst()
                .orElse(null);

        if (reservedBed != null) {
            reservedBed.setReserved(true);
            Reservation reservation = new Reservation(host, user, Status.PENDING);
            reservationRepository.save(reservation);
            return reservation;
        } else {
            throw new IllegalArgumentException("Bed with the specified bedId not found in the host's list of beds.");
        }
    }

    public List<Reservation> getReservationsByHostId(String hostId) {
        return reservationRepository.getAllReservations().stream()
                .filter(reservation ->
                        reservation.getHost().getHostId().equals(hostId))
                .collect(Collectors.toList());
    }


    public List<Reservation> getReservationsByHostIdStatusPending(String hostId) {
        return reservationRepository.getAllReservations().stream()
                .filter(reservation ->
                        reservation.getHost().getHostId().equals(hostId) &&
                                reservation.getStatus() == Status.PENDING)
                .collect(Collectors.toList());
    }


    public List<Reservation> approveReservations(List<String> reservationsId, String hostId) {
        List<Reservation> reservations = reservationRepository.getAllReservations().stream()
                .filter(res -> {
                    if (reservationsId.contains(res.getReservationId()) && res.getHost().getHostId().equals(hostId)) {
                        res.setStatus(Status.RESERVED);
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
        reservationRepository.saveAll(reservations);
        return reservations;
    }


    public List<Reservation> rejectReservations(List<String> reservationsId, String hostId) {
        List<Reservation> reservations = reservationRepository.getAllReservations().stream()
                .filter(res -> {
                    if (reservationsId.contains(res.getReservationId()) && res.getHost().getHostId().equals(hostId)) {
                        res.setStatus(Status.CANCELLED);
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
        reservationRepository.saveAll(reservations);
        return reservations;
    }
    public List<Reservation> getReservationsByHostIdStatusReserved(String hostId) {
        return reservationRepository.getAllReservations().stream()
                .filter(reservation ->
                        reservation.getHost().getHostId().equals(hostId) &&
                                reservation.getStatus() == Status.RESERVED)
                .collect(Collectors.toList());
    }
}
