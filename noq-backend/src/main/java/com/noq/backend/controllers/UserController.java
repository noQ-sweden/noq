package com.noq.backend.controllers;

import com.noq.backend.DTO.UserDTO;
import com.noq.backend.models.User;
import com.noq.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/getall")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(UserController::userDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/user")
    public UserDTO getUser() {
        return userDTO(userService.getUser());
    }


    private static UserDTO userDTO(User user) { // DO WE INCLUDE THE ID property? Unsure here!
        return new UserDTO(
               user.getId(), // null?
                user.getName(),
                user.getReservation()
        );
    }
}
