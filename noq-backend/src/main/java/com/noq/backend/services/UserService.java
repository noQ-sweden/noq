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

    private final EmailService emailService;

    // FIXME Generate a unokodCache on Server Startup, so that new unokods can be created without risk of duplication
    private final Set<String> unokodCache;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.unokodCache = new HashSet<>();
        this.emailService = emailService;
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
    // Generate a new UNOKOD and build the user with it
    User newUser = user.toBuilder()
            .unokod(generateUnoKod(user.getFirstName(), user.getLastName(), user.getDateOfBirth()))
            .build();

    // Save the user
    userRepository.save(newUser);

    // Send confirmation email
    if (newUser.getEmail() != null && !newUser.getEmail().isBlank()) {
        String subject = "noQ Confirmation Email";
        String body = String.format(
                "Hi!\n\nWelcome to noQ!\n\nThanks for joining us!\n- The noQ Team",
                newUser.getFirstName(),
                newUser.getUnokod()
        );
        emailService.sendConfirmationEmail(newUser.getEmail(), subject, body);
    } else {
        log.warn("Invalid email. Unable to send confirmation email for user: {}", newUser.getId());
    }

    return newUser;
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
