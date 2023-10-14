package com.noq.backend.controllers.host.VacanciesViewController;

import com.noq.backend.controllers.host.BedsViewController.BedDTO;
import com.noq.backend.controllers.might_delete.DTOs.CreateReservationDTO;
import com.noq.backend.models.Host;
import com.noq.backend.services.BedService;
import com.noq.backend.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/host/vacancies")
public class VacanciesViewController {
    private final BedService beds;
    private final ReservationService reservations;

    @GetMapping
    public Flux<VacancyViewDTO> getAllVacancies() {
        return beds
                .findAllBeds()
                .filter(bed -> !bed.reserved())
                .map(this::toDTO)
                .switchIfEmpty(Flux.empty());
    }

    @PostMapping("/create-reservation")
    public Mono<ResponseEntity<CreateReservationDTO>> createReservation(@RequestBody CreateReservationDTO request) {
        return reservations
                .createReservation(request)
                .map(reservation -> {
                    CreateReservationDTO response = new CreateReservationDTO(
                            reservation.getHost().getHostId(),
                            reservation.getUser().getId(),
//                          TODO: Add bedId to Reservation-model?
                            request.bedId()
                    );
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                });
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    private VacancyViewDTO toDTO(BedDTO bed) {
        Host h = bed.host();
        VacancyViewDTO.Host hostDTO = new VacancyViewDTO.Host(
                h.getHostId(),
                h.getName(),
                h.getAddress(),
                h.getImage()
        );
        VacancyViewDTO.Vacancy vacancyDTO = new VacancyViewDTO.Vacancy(hostDTO, bed.id());
        return new VacancyViewDTO(vacancyDTO);
    }
}

