package com.noq.backend.controllers;

import com.noq.backend.dto.BedDTO;
import com.noq.backend.dto.HostDTO;
import com.noq.backend.dto.ReservationDTO;
import com.noq.backend.services.HostService;
import com.noq.backend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/get-reservation")
    public ReservationDTO getReservation(@Param("userId") String userId) {
        return reservationService.getReservationByUserId(userId);
    }

    @PostMapping("/create-reservation")
    public String createReservation(@Param("userId") String userId) {
       return "";
    }
}
