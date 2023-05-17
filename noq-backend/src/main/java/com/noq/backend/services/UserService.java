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

    public User getUser() {
        return new User(
                UUID.randomUUID().toString(),
                "Person Personsson",
                false
        );
    }

    List<User> userList = List.of( // Mock Data for testing
            new User(
                    UUID.randomUUID().toString(),
                    "Person Personsson",
                    false
            ),
            new User(
                    UUID.randomUUID().toString(),
                    "Individ Individson",
                    true
            )
    );
}
