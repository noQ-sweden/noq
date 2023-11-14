package com.noq.backend.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(new ArrayList<>());
    }

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