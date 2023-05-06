package com.noq.backend.services;

import com.noq.backend.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    public List<User> getAllUsers() {
        return userList;
    }

    List<User> userList = List.of( // Mock Data for testing
            new User(
                    UUID.randomUUID(),
                    "Person Personsson",
                    false
            ),
            new User(
                    UUID.randomUUID(),
                    "Individ Individson",
                    true
            )
    );
}
