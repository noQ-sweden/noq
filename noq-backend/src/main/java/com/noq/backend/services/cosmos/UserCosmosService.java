package com.noq.backend.services.cosmos;

import com.azure.cosmos.models.PartitionKey;
import com.noq.backend.DTO.cosmos.UserCosmosDTO;
import com.noq.backend.models.cosmos.UserCosmos;
import com.noq.backend.repository.cosmos.UserRepositoryCosmos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserCosmosService {

    private final UserRepositoryCosmos userRepositoryCosmos;

    // CREATE NEW USER
    public Mono<UserCosmosDTO> create(UserCosmosDTO request) {
        UserCosmos user = new UserCosmos(
                request.name(),
                request.reservation()
        );

        System.out.println("Creating User with id: " + user.getId());

        return userRepositoryCosmos.save(user)
                .map(this::toDTO)
                .onErrorResume(this::handleError);
    }

    // GET USER BY ID
    public Mono<UserCosmosDTO> findById(String id) {

        System.out.println("Searching user: " + id);

        return userRepositoryCosmos.findById(id, new PartitionKey(id))
                .map(this::toDTO)
                .onErrorResume(this::handleNotFoundError);
    }

    // GET ALL USERS
    public Flux<UserCosmosDTO> findAll() {
        System.out.println("Listing all users...");
        return userRepositoryCosmos.findAll()
                .map(this::toDTO)
                .onErrorResume(this::handleError);
    }


    // ERROR HANDLING ####################################################################################
    private Mono<UserCosmosDTO> handleError(Throwable error) {
        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Oops! Something went wrong!", error));
    }

    private Mono<UserCosmosDTO> handleNotFoundError(Throwable error) {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found.", error));
    }
    // ###################################################################################################

    // DTO CONVERTER
    private UserCosmosDTO toDTO(UserCosmos user) {
        return new UserCosmosDTO(
                user.getId(),
                user.getName(),
                user.getReservation()
        );
    }



}
