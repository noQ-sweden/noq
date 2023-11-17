package com.noq.backend;

import com.noq.backend.repositories.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NoqBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoqBackendApplication.class, args);
    }
}
