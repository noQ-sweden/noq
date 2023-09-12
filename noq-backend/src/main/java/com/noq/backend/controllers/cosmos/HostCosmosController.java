package com.noq.backend.controllers.cosmos;

import com.noq.backend.DTO.HostCosmosDTO;
import com.noq.backend.services.cosmos.HostCosmosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/hostCosmos")
public class HostCosmosController {

    private final HostCosmosService hostCosmosService;

    // CREATE NEW HOST
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<HostCosmosDTO>> create(@RequestBody HostCosmosDTO request) {
        return hostCosmosService.create(request)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .onErrorResume(error -> INTERNAL_SERVER_ERROR_RESPONSE);
    }

    //GET HOST WITH ID
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<HostCosmosDTO>> getUserById(@PathVariable("id") String id,
                                                           @RequestBody HostCosmosDTO request) {
        return hostCosmosService.findById(request.hostId(), request.name())
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .switchIfEmpty(NOT_FOUND_RESPONSE)
                .onErrorResume(error -> INTERNAL_SERVER_ERROR_RESPONSE);
    }

    //GET ALL HOSTS
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<HostCosmosDTO> getAllUsers() {
        return hostCosmosService.findAll().onErrorResume(error -> Mono.error(
                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database not responding.")
        ));
    }

    // ERROR HANDLING #############################################################################
    private static final Mono<ResponseEntity<HostCosmosDTO>> INTERNAL_SERVER_ERROR_RESPONSE =
            Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    private static final Mono<ResponseEntity<HostCosmosDTO>> NOT_FOUND_RESPONSE =
            Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    // ############################################################################################

}
