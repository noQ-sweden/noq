package com.noq.backend.services;

import com.noq.backend.models.Address;
import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class HostService {


    public List<Host> getAllHosts() {
        return hostList;
    }


    List<Host> hostList = List.of( // Service with Mock-Data for test
            new Host(
                    1L,
                    "Test-Härberget 1",
                    new Address(
                            1L,
                            "Gatgatan 12",
                            "Stockholm",
                            "Södermanland",
                            "12345"
                    ),
                    new HashSet<Bed>(5)
            ),
            new Host(
                    2L,
                    "Test-Härberget 2",
                    new Address(
                            2L,
                            "Vägvägen 21",
                            "Lund",
                            "Skåne",
                            "24536"
                    ),
                    new HashSet<Bed>(3)
            )
    );
}

