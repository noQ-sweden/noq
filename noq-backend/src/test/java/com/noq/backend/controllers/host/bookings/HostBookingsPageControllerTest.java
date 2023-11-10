package com.noq.backend.controllers.host.bookings;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
@Disabled("To be Enabled after implementing Postgres Functionality")
class HostBookingsPageControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @SneakyThrows
    @Test
    void shouldReturnOK() {

    }

}