package com.noq.backend.services;

import com.noq.backend.models.Host;
import com.noq.backend.services.HostService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
class HostServiceTest {
    @Autowired
    HostService hostService;

    @Test
    void create() {
    }

    @Test
    void findByHostId() {
        String hostId = "efe102ca-e3e9-42b4-a722-f0fbc0306b7a";
        Host block = hostService.findByHostId(hostId).block();
        System.out.println(block);
    }

    @Test
    void findById() {
    }

    @Test
    void testFindById() {
    }

    @Test
    void findAll() {
    }
}
