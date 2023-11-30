package com.noq.backend;

import com.noq.backend.models.Host;
import com.noq.backend.models.User;
import com.noq.backend.repositories.HostRepository;
import com.noq.backend.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NoqBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoqBackendApplication.class, args);
    }
}
