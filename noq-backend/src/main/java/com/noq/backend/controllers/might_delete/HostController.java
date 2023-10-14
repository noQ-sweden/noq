package com.noq.backend.controllers.might_delete;

import com.noq.backend.controllers.might_delete.DTOs.HostDTO;
import com.noq.backend.services.HostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/v1/host")
public class HostController {

    private final HostService hostCosmosService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<HostDTO>> create(@RequestBody HostDTO request) {
        return hostCosmosService.create(request)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .onErrorResume(error -> INTERNAL_SERVER_ERROR_RESPONSE);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<HostDTO>> getUserById(@PathVariable("id") String id,
                                                     @RequestBody HostDTO request) {
        return hostCosmosService.findById(request.hostId(), request.name())
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .switchIfEmpty(NOT_FOUND_RESPONSE)
                .onErrorResume(error -> INTERNAL_SERVER_ERROR_RESPONSE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<HostDTO> getAllUsers() {
        return hostCosmosService.findAll().onErrorResume(error -> Mono.error(
                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database not responding.")
        ));
    }

    private static final Mono<ResponseEntity<HostDTO>> INTERNAL_SERVER_ERROR_RESPONSE =
            Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    private static final Mono<ResponseEntity<HostDTO>> NOT_FOUND_RESPONSE =
            Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

}
