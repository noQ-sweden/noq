package com.noq.backend.controllers;

import com.noq.backend.DTO.AddressDTO;
import com.noq.backend.DTO.ReservationDTO;
import com.noq.backend.DTO.VacancyDTO;
import com.noq.backend.models.CreateReservation;
import com.noq.backend.models.Host;
import com.noq.backend.models.Vacancy;
import com.noq.backend.repository.HostRepository;
import com.noq.backend.services.HostService;
import com.noq.backend.services.ReservationService;
import com.noq.backend.services.VacanciesViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/vacancies")
public class VacanciesViewController {

    private final VacanciesViewService vacanciesViewService;
    private final HostService hostService;
    private final ReservationService reservationService;

    @Autowired
    public VacanciesViewController(VacanciesViewService vacanciesViewService, HostService hostService, ReservationService reservationService) {
        this.vacanciesViewService = vacanciesViewService;
        this.hostService = hostService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<VacancyDTO> getAllVacancies() {
        return vacanciesViewService.getAllVacancies().stream().map(VacanciesViewController::toDTO).collect(Collectors.toList());
    }

    @PostMapping("/create-reservation")
    public ResponseEntity<CreateReservation> createReservation(@RequestBody CreateReservation createReservation) {
        reservationService.createReservation(createReservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createReservation);
    }

    private static VacancyDTO toDTO(Vacancy vacancy) {
        return new VacancyDTO(
                vacancy.getHostId(),
                vacancy.getHostName(),
                vacancy.getHostImg(),
                vacancy.getAddress(),
                vacancy.getBedId()
        );
    }
}

