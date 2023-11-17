package com.noq.backend.services;

import com.noq.backend.models.User;
import com.noq.backend.repositories.UserRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    public static final int MIN_LENGTH_UNOKOD = 6;

    private final UserRepository userRepository;

    // FIXME Generate a unokodCache on Server Startup, so that new unokods can be created without risk of duplication
    private final Set<String> unokodCache;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.unokodCache = new HashSet<>();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUnokod(String unokod) {
        // Implement logic to find and return a user by UNOKOD
        // TODO
        throw new RuntimeException("getUserByUnokod Not Implemented");
    }

    public User getUserById(UUID id) {
        // TODO Improve Error Handling
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public User createUser(User user) {
        userRepository.save(user.toBuilder()
                .unokod(generateUnoKod(user.getFirstName(), user.getLastName(), user.getDateOfBirth()))
                .build());
        return user;
    }

    /**
     * Custom Business Logic to Generate New UNOKOD.
     * If a duplicate is encountered on generation then it appends 1, 2, 3 and so on to every next one to keep UNOKOD simple as possible to remember.
     */
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
        userRepository.save(user.toBuilder()
                .unokod(generateUnoKod(user.getFirstName(), user.getLastName(), user.getDateOfBirth()))
                .build());
        return user;
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
