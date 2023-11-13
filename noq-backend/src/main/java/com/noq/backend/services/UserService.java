package com.noq.backend.services;

import com.noq.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    // TODO private final UserRepository userRepository;

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


    // DTO CONVERTER
//    private UserDTO toDTO(User user) {
//        return new UserDTO(
//                user.getId(),
//                user.getName(),
//                user.getReservation()
//        );
//    }


}
