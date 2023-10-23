package com.noq.backend.services;

import com.azure.cosmos.models.PartitionKey;
import com.noq.backend.controllers.might_delete.DTOs.UserDTO;
import com.noq.backend.models.User;
import com.noq.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // CREATE NEW USER
//    public Mono<UserDTO> create(UserDTO request) {
//        User user = User.create(request.name(), request.reservation());
//
////        System.out.println("Creating User with id: " + user.getId());
//
//        return userRepository.save(user)
//                .map(this::toDTO)
//                .onErrorResume(this::handleError);
//    }

    // GET USER BY ID
//    public Mono<UserDTO> findById(String id) {
//
//        System.out.println("Searching user: " + id);
//
//        return userRepository.findById(id, new PartitionKey(id))
//                .map(this::toDTO)
//                .onErrorResume(this::handleNotFoundError);
//    }

    // GET ALL USERS
//    public Flux<UserDTO> findAll() {
//        System.out.println("Listing all users...");
//        return userRepository.findAll()
//                .map(this::toDTO)
//                .onErrorResume(this::handleError);
//    }


    // ERROR HANDLING ####################################################################################
    private Mono<UserDTO> handleError(Throwable error) {
        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Oops! Something went wrong!", error));
    }

    private Mono<UserDTO> handleNotFoundError(Throwable error) {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found.", error));
    }
    // ###################################################################################################

    // DTO CONVERTER
//    private UserDTO toDTO(User user) {
//        return new UserDTO(
//                user.getId(),
//                user.getName(),
//                user.getReservation()
//        );
//    }


}
