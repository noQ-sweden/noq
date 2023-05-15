package com.noq.backend.services;

import com.noq.backend.dto.UserDTO;
import com.noq.backend.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    public List<UserDTO> getAllUsers() {
        return userList.stream().map(this::toUserDTO)
                .collect(Collectors.toList());
    }

    List<User> userList = List.of( // Mock Data for testing
            new User(
                    "Person Personsson",
                    "Person Personsson"
            ),
            new User(
                    "Individ Individson",
                    "Individ Individson"
            )
    );

    private UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getPassword(),
                user.isReservation()
        );
    }
}
