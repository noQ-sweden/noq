package com.noq.backend.services;

import com.noq.backend.models.User;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserService {

    public static final int MIN_LENGTH_UNOKOD = 6;
    private final List<User> users;
    // FIXME Generate a unokodCache on Server Startup
    private final Set<String> unokodCache;

    public UserService(ArrayList<User> users) {
        this.users = users;
        this.unokodCache = new HashSet<>();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserByUnokod(String unokod) {
        // Implement logic to find and return a user by ID
        return null;
    }

    public User getUserById(UUID id) {
        // Implement logic to find and return a user by ID
        return null;
    }

    public User createUser(User user) {
        // Implement logic to create a new user
        users.add(user);
        return user;
    }

    String generateUnoKod(@NonNull String firstName, @NonNull String lastName, @NonNull String dateOfBirth) {
        var generatedUnoKod = String.valueOf(firstName.toUpperCase().charAt(0)) + lastName.toUpperCase().charAt(0) + dateOfBirth.substring(4);
        if (!unokodCache.isEmpty() && unokodCache.contains(generatedUnoKod)) {
            var initiallyGeneratedUnoKod = generatedUnoKod;
            int repeatingUnkodCount = unokodCache.stream()
                    .filter(unoKod -> unoKod.length() > MIN_LENGTH_UNOKOD)
                    .filter(unoKod -> unoKod.startsWith(initiallyGeneratedUnoKod))
                    .mapToInt(unoKod -> Integer.parseInt(unoKod.substring(unoKod.length() - 1)))
                    .max().orElse(0);
            generatedUnoKod += ++repeatingUnkodCount;
        }
        log.info("Generated UNOKOD {} for {} {}", generatedUnoKod, firstName, lastName);
        unokodCache.add(generatedUnoKod);
        return generatedUnoKod;
    }

    public User updateUser(UUID id, User user) {
        // Implement logic to update an existing user
        return null;
    }

    public void deleteUser(Long id) {
        // Implement logic to delete a user by ID
    }
}
