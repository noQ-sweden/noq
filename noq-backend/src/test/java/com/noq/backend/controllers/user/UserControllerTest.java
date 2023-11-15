package com.noq.backend.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noq.backend.models.User;
import com.noq.backend.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsers() throws Exception {
        List<User> users = Arrays.asList(
                User.builder().userId(UUID.randomUUID()).firstName("user1FName")
                        .lastName("user1LName")
                        .email("user1@example.com")
                        .unokod("unokod1")
                        .dateOfBirth("19630304")
                        .caseManager("Ordnings Vakt")
                        .build(), User.builder()
                        .userId(UUID.randomUUID())
                        .firstName("user2FName")
                        .lastName("user2LName")
                        .email("user2@example.com")
                        .unokod("unokod2")
                        .dateOfBirth("19630304")
                        .caseManager("Härbärge Forvaltare")
                        .build());

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("user1FName"))
                .andExpect(jsonPath("$[1].firstName").value("user2FName"));
    }

    @Test
    void getUserById() throws Exception {
        var user = User.builder()
                .userId(UUID.randomUUID())
                .firstName("Bengt")
                .lastName("Bergström")
                .dateOfBirth("19630304")
                .unokod("BB0304")
                .email("BB@Test.com")
                .caseManager("Ordnings Vakt")
                .build();

        when(userService.getUserById(user.getUserId())).thenReturn(user);

        mockMvc.perform(get("/api/users/id/{id}", user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Bengt"));
    }

    @Test
    void createUser() throws Exception {
        var newUser = User.builder()
                .userId(UUID.randomUUID())
                .firstName("Bengt")
                .lastName("Bergström")
                .dateOfBirth("19630304")
                .unokod("BB0304")
                .email("BB@Test.com")
                .caseManager("Ordnings Vakt")
                .build();

        when(userService.createUser(any(User.class))).thenReturn(newUser);
        System.out.println(asJsonString(newUser));
        mockMvc.perform(post("/api/users")
                        .content(asJsonString(newUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").isNotEmpty())
                .andExpect(jsonPath("$.lastName").value("Bergström"))
                .andExpect(jsonPath("$.firstName").value("Bengt"));
    }

    @Test
    void updateUser() {
        // TODO When Required. Not in Focus for the first iteration.
    }

    @Test
    void deleteUser() throws Exception {
        UUID userId = UUID.randomUUID();

        mockMvc.perform(delete("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    // Helper method to convert object to JSON string
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
