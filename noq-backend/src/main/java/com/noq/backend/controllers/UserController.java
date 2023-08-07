package com.noq.backend.controllers;

import com.noq.backend.DTO.ReservationDTO;
import com.noq.backend.DTO.UserDTO;
import com.noq.backend.models.User;
import com.noq.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/get-all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(UserController::toUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return toUserDTO(user);
    }



    private static UserDTO toUserDTO(User user) {
        ReservationDTO reservationDTO = new ReservationDTO(
                user.getReservtaion().getReservationId(),
                user.getReservtaion().getHost(),
                user,
                user.getReservtaion().getStatus()
        );

        return new UserDTO(
                user.getId(),
                user.getName(),
                reservationDTO
        );
    }
}
