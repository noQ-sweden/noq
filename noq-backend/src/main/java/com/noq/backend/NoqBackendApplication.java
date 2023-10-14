package com.noq.backend;

import com.noq.backend.models.Address;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.models.User;
import com.noq.backend.repository.HostRepository;
import com.noq.backend.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class NoqBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoqBackendApplication.class, args);
    }

    @Bean
    public WebClient webclient() {
        return WebClient.create();
    }

    @Bean
    ApplicationRunner applicationRunner (HostRepository hostRepository,
                                         UserRepository userRepository){
        return args -> {
            User user1 = new User(
                    "User",
                    new Reservation()
            );
            Host host1 = new Host(
                    "HostCosmos1",
                    new Address("Gatgatan", "1A", "152 42", "Årsta"),
                    "URL TILL BILD"
            );
            hostRepository.save(host1).subscribe();
            userRepository.save(user1).subscribe();
        };
    }

}
