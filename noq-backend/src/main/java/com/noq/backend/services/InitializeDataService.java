package com.noq.backend.services;


import com.noq.backend.models.cosmos.HostCosmos;
import com.noq.backend.models.cosmos.AddressCosmos;
import com.noq.backend.models.cosmos.HostCosmos;
import com.noq.backend.models.cosmos.ReservationCosmos;
import com.noq.backend.models.cosmos.UserCosmos;
import com.noq.backend.repository.cosmos.HostRepositoryCosmos;
import com.noq.backend.repository.cosmos.UserRepositoryCosmos;
import com.noq.backend.services.cosmos.HostCosmosService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InitializeDataService {

    private UserService userService;
    private HostService hostService;
    private HostRepositoryCosmos hostRepositoryCosmos;
    private UserRepositoryCosmos userRepositoryCosmos;

    @Autowired
    public InitializeDataService(UserService userService,
                                 HostService hostService,
                                 HostRepositoryCosmos hostRepositoryCosmos,
                                 UserRepositoryCosmos userRepositoryCosmos) {
        this.userService = userService;
        this.hostService = hostService;
        this.hostRepositoryCosmos = hostRepositoryCosmos;
        this.userRepositoryCosmos = userRepositoryCosmos;
    }

    @PostConstruct
    public void initializeData() {
        userService.createUsers();
        hostService.createHosts();





        UserCosmos user1 = new UserCosmos(
                "User",
                new ReservationCosmos()
        );
        userRepositoryCosmos.save(user1).block();

        HostCosmos host1 = new HostCosmos(
                "HostCosmos1",
                new AddressCosmos("Gatgatan", "1A", "152 42", "Årsta"),
                "URL TILL BILD"
        );
        hostRepositoryCosmos.save(host1).block();

    }
}
