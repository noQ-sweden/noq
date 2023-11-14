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
                        .build(), User.builder()
                        .userId(UUID.randomUUID())
                        .firstName("user2FName")
                        .lastName("user2LName")
                        .email("user2@example.com")
                        .unokod("unokod2")
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
        User user = User.builder().build();

        when(userService.getUserById(user.getUserId())).thenReturn(user);

        mockMvc.perform(get("/api/users/{id}", user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("user1"));
    }

    @Test
    void createUser() throws Exception {
        User newUser = User.builder().build();
        User createdUser = User.builder().build();

        when(userService.createUser(any(User.class))).thenReturn(createdUser);

        mockMvc.perform(post("/api/users")
                        .content(asJsonString(newUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").isNotEmpty())
                .andExpect(jsonPath("$.name").value("newUser"));
    }

    @Test
    void updateUser() throws Exception {
        UUID userId = UUID.randomUUID();
        User updateUser = User.builder().build();

        when(userService.updateUser(userId, updateUser)).thenReturn(updateUser);

        mockMvc.perform(put("/api/users/{id}", userId)
                        .content(asJsonString(updateUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("updatedUser"));
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
