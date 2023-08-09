package com.noq.backend.controllers;

import com.noq.backend.DTO.ReservationDTO;
import com.noq.backend.DTO.UserDTO;
import com.noq.backend.DTO.UserPageDTO;
import com.noq.backend.models.User;
import com.noq.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-page")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserPageController {

    private final UserService userService;
    @Autowired
    public UserPageController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/get-all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(UserPageController::toUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserPageDTO getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return toDTO(user);
    }


private static UserPageDTO toDTO (User user) {
        return new UserPageDTO(user.getId(), user.getName());
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
