package com.noq.backend.services;

import com.noq.backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository);
    }

    // TODO More Test Cases for Creating Users and Error Handling etc

    @Test
    void shouldCreateUNOKOD_whenCreatingUsersAutomatically() {
        var firstName = "Bengt";
        var lastName = "Bergström";
        var dateOfBirth = "19630304";
        assertThat(userService.generateUnoKod(firstName, lastName, dateOfBirth)).isEqualTo("BB0304");
    }

    @Test
    void shouldCreateADifferentUNOKOD_whenTheSimplestAlternativeExists() {
        var firstName = "Bengt";
        var lastName = "Bergström";
        var dateOfBirth = "19630304";
        assertThat(userService.generateUnoKod(firstName, lastName, dateOfBirth)).isEqualTo("BB0304");
        assertThat(userService.generateUnoKod(firstName, lastName, dateOfBirth)).isEqualTo("BB03041");
        assertThat(userService.generateUnoKod(firstName, lastName, dateOfBirth)).isEqualTo("BB03042");
        assertThat(userService.generateUnoKod(firstName, lastName, dateOfBirth)).isEqualTo("BB03043");
        assertThat(userService.generateUnoKod(firstName, lastName, dateOfBirth)).isEqualTo("BB03044");
        assertThat(userService.generateUnoKod(firstName, lastName, dateOfBirth)).isEqualTo("BB03045");
    }

}