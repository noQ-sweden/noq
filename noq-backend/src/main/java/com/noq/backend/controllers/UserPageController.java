package com.noq.backend.controllers;

import com.noq.backend.DTO.UserPageDTO;
import com.noq.backend.models.User;
import com.noq.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-page")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserPageController {

    private final UserService userService;
    @Autowired
    public UserPageController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{userId}")
    public UserPageDTO getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return toDTO(user);
    }


private static UserPageDTO toDTO (User user) {
        return new UserPageDTO(user.getId(), user.getName());
}

}
