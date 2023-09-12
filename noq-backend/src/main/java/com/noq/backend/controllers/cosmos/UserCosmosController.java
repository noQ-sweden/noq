package com.noq.backend.controllers.cosmos;

import com.noq.backend.DTO.cosmos.HostCosmosDTO;
import com.noq.backend.DTO.cosmos.UserCosmosDTO;
import com.noq.backend.services.cosmos.UserCosmosService;
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
@RequestMapping(path = "/userCosmos")
public class UserCosmosController {

    private final UserCosmosService userCosmosService;


    // CREATE NEW USER
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserCosmosDTO>> create(@RequestBody UserCosmosDTO request) {
        return userCosmosService.create(request)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .onErrorResume(error -> INTERNAL_SERVER_ERROR_RESPONSE);
    }

    //GET USER WITH ID
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserCosmosDTO>> getUserById(@PathVariable("id") String id,
                                                           @RequestBody UserCosmosDTO request) {
        return userCosmosService.findById(request.id())
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .switchIfEmpty(NOT_FOUND_RESPONSE)
                .onErrorResume(error -> INTERNAL_SERVER_ERROR_RESPONSE);
    }

    //GET ALL USERS
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<UserCosmosDTO> getAllUsers() {
        return userCosmosService.findAll()
                .onErrorResume(error ->
                        Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database not responding.")
                        ));
    }

    // ERROR HANDLING #############################################################################
    private static final Mono<ResponseEntity<UserCosmosDTO>> INTERNAL_SERVER_ERROR_RESPONSE =
            Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    private static final Mono<ResponseEntity<UserCosmosDTO>> NOT_FOUND_RESPONSE =
            Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    // ############################################################################################


}
