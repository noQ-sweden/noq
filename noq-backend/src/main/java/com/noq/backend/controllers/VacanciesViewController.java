package com.noq.backend.controllers;

import com.noq.backend.DTO.VacancyViewDTO;
import com.noq.backend.models.CreateReservation;
import com.noq.backend.models.Vacancy;
import com.noq.backend.services.ReservationService;
import com.noq.backend.services.VacanciesViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/vacancies")
public class VacanciesViewController {

    private final VacanciesViewService vacanciesViewService;
    private final ReservationService reservationService;

    @Autowired
    public VacanciesViewController(VacanciesViewService vacanciesViewService, ReservationService reservationService) {
        this.vacanciesViewService = vacanciesViewService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public VacancyViewDTO getAllVacancies() {
        return toDTO(vacanciesViewService.getAllVacancies());
    }

    @PostMapping("/create-reservation")
    public ResponseEntity<CreateReservation> createReservation(@RequestBody CreateReservation createReservation) {
        reservationService.createReservation(createReservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createReservation);
    }

    private static VacancyViewDTO toDTO(List<Vacancy> vacancy) {
        List<VacancyViewDTO.Vacancy> vacancies = vacancy.stream()
                .map(vacancy1 -> {
                    VacancyViewDTO.Host host = new VacancyViewDTO.Host(
                            vacancy1.getHostId(), vacancy1.getHostName(), vacancy1.getAddress(), vacancy1.getHostImg());
                    VacancyViewDTO.Vacancy res = new VacancyViewDTO.Vacancy(host, vacancy1.getBedId());
                    return res;
                }).collect(Collectors.toList());

        return new VacancyViewDTO(
                vacancies);
    }
}

