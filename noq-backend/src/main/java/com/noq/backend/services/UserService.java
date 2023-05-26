package com.noq.backend.services;

import com.noq.backend.models.User;
import com.noq.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public List<User> createUsers() {
        User user1 = new User(
                "1",
                "Person Personsson",
                false
        );
        User user2 = new User(
                "2",
                "Individ Individson",
                true
        );
        userRepository.save(user1);
        userRepository.save(user2);
        return new ArrayList<>(userRepository.getAllUsers());
    }

    public User getUserById(String userId) {
        User existingUser = userRepository.getUserByUserId(userId);
        if (existingUser != null) {
            return existingUser;
        } else {
            return userRepository.getUserByUserId(userId);
        }
    }
}
