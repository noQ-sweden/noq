package com.noq.backend.controllers.cosmos;

import com.noq.backend.DTO.cosmos.BedDTO;
import com.noq.backend.DTO.cosmos.CreateReservationDTO;
import com.noq.backend.DTO.cosmos.VacancyViewCosmosDTO;
import com.noq.backend.models.cosmos.HostCosmos;
import com.noq.backend.services.cosmos.BedCosmosService;
import com.noq.backend.services.cosmos.ReservationCosmosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/vacancies")
public class VacanciesViewCosmosController {
    private final BedCosmosService beds;
    private final ReservationCosmosService reservations;

    @GetMapping
    public Flux<VacancyViewCosmosDTO> getAllVacancies() {
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

    private VacancyViewCosmosDTO toDTO(BedDTO bed) {
        HostCosmos h = bed.host();
        VacancyViewCosmosDTO.Host hostDTO = new VacancyViewCosmosDTO.Host(
                h.getHostId(),
                h.getName(),
                h.getAddress(),
                h.getImage()
        );
        VacancyViewCosmosDTO.Vacancy vacancyDTO = new VacancyViewCosmosDTO.Vacancy(hostDTO, bed.id());
        return new VacancyViewCosmosDTO(vacancyDTO);
    }
}

