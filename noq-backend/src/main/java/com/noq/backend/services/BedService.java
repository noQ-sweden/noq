package com.noq.backend.services;

import com.noq.backend.repositories.BedRepository;
import com.noq.backend.repositories.HostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BedService {
    private final BedRepository beds;
    private final HostRepository hosts;
}
