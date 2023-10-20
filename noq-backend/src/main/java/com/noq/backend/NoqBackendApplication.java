package com.noq.backend;

import com.azure.spring.data.cosmos.repository.config.EnableReactiveCosmosRepositories;
import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import com.noq.backend.models.Reservation;
import com.noq.backend.models.User;
import com.noq.backend.repositories.BedRepository;
import com.noq.backend.repositories.HostRepository;
import com.noq.backend.repositories.ReservationRepository;
import com.noq.backend.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootApplication
@EnableReactiveCosmosRepositories
public class NoqBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoqBackendApplication.class, args);
    }

    @Bean
    public WebClient webclient() {
        return WebClient.create();
    }

    @Bean
    ApplicationRunner applicationRunner(HostRepository hostRepository,
                                        UserRepository userRepository,
                                        ReservationRepository reservationRepository,
                                        BedRepository bedRepository
    ) {
        return args -> {
            User user1 = User.create("User", null);
            userRepository.save(user1).subscribe();

            Host.Address address1 = Host.Address.create("street1", "1", "000", "d");
            Host.Address address2 = Host.Address.create("street1", "1", "000", "d");
            Host host1 = Host.create("HostCosmos1", List.of(address1, address2), "");
            hostRepository.save(host1).subscribe();

            Reservation reservation1 = Reservation.create(host1, user1);
            reservationRepository.save(reservation1).subscribe();

            Bed bed1 = Bed.create(host1, host1.getHostId(), false);
            bedRepository.save(bed1).subscribe();
        };
    }

}
