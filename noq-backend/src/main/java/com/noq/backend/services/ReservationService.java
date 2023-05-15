package com.noq.backend.services;

import com.noq.backend.dto.BedDTO;
import com.noq.backend.dto.HostDTO;
import com.noq.backend.dto.ReservationDTO;
import com.noq.backend.dto.UserDTO;
import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.models.User;
import com.noq.backend.repository.BedRepository;
import com.noq.backend.repository.ReservationRepository;
import com.noq.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private UserRepository userRepository;
    private BedRepository bedRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(UserRepository userRepository, BedRepository bedRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.bedRepository = bedRepository;
        this.reservationRepository = reservationRepository;
    }


    public ReservationDTO getReservationByUserId(String userId) {
      return toReservationDTO(reservationRepository.getReservationByUserId(userId));
    }

   private User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow();
   }
    private Bed getBedById(String bedId) {
        return bedRepository.findById(bedId).orElseThrow();
    }

    private ReservationDTO toReservationDTO(Reservation reservation) {
        return new ReservationDTO(
                toBedDTO(reservation.getBed()),
                toUserDTO(reservation.getUser()),
                reservation.getReservedTime()
        );
    }

    private BedDTO toBedDTO(Bed bed) {
        return new BedDTO(
                bed.getPrice(),
                toHostDTO(bed.getHost())
        );
    }

    private HostDTO toHostDTO(Host host) {
        return new HostDTO(
                host.getName(),
                host.getAddress(),
                host.getBedIds()
        );
    }
    private UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getPassword(),
                user.isReservation()
        );
    }
}
