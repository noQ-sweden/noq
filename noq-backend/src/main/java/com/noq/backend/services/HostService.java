package com.noq.backend.services;

import com.noq.backend.models.Address;
import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class HostService {


    public List<Host> getAllHosts() {
        return hostList;
    }


    List<Host> hostList = List.of( // Service with Mock-Data for test
            new Host(
                    UUID.randomUUID(),
                    "Test-Härberget 1",
                    new Address(
                            UUID.randomUUID(),
                            "Gatgatan",
                            "12",
                            "12345",
                            "Stockholm"
                    ),
                    "url/till/bild/pa/Harberget1.png",
                    15L
            ),
            new Host(
                    UUID.randomUUID(),
                    "Test-Härberget 2",
                    new Address(
                            UUID.randomUUID(),
                            "Vägvägen",
                            "21",
                            "23546",
                            "Lund"
                    ),
                    "url/till/bild/pa/Harberget2.png",
                    20L
            )
    );
}

