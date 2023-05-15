package com.noq.backend.services;

import com.noq.backend.dto.HostDTO;
import com.noq.backend.models.Address;
import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HostService {

    public List<HostDTO> getAllHosts() {
        return hostList.stream().map(this::toHostDTO)
                .collect(Collectors.toList());
    }

    List<Host> hostList = List.of( // Service with Mock-Data for test
            new Host(
                    "Test-Härberget 1",
                    new Address(
                            "Gatgatan 12",
                            "Stockholm",
                            "Södermanland",
                            "12345"
                    ),
                    new HashSet<Bed>(5)
            ),
            new Host(
                    "Test-Härberget 2",
                    new Address(
                            "Vägvägen 21",
                            "Lund",
                            "Skåne",
                            "24536"
                    ),
                    new HashSet<Bed>(3)
            )
    );

    private HostDTO toHostDTO(Host host) {
        return new HostDTO(
                host.getName(),
                host.getAddress(),
                host.getBedIds()
        );
    }
}

