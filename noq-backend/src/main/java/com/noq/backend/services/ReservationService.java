package com.noq.backend.services;

import com.noq.backend.models.*;
import com.noq.backend.repository.HostRepository;
import com.noq.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationService {

    private HostRepository hostRepository;
    private UserRepository userRepository;

    @Autowired
    public ReservationService(HostRepository hostRepository, UserRepository userRepository) {
        this.hostRepository = hostRepository;
        this.userRepository = userRepository;
    }


    public Reservation createReservation(CreateReservation createReservation) {
        User user = userRepository.getUserByUserId(createReservation.getUserId());
        Host host = hostRepository.getHostByHostId(createReservation.getHostId());
        System.out.print(createReservation.getHostId());
        Reservation reservation = new Reservation(host, user, Status.RESERVED);
        System.out.print(reservation);
        return reservation;
    }
}
