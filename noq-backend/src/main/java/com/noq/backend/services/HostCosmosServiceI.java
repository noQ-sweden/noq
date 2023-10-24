package com.noq.backend.services;

import com.noq.backend.models.Host;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface HostCosmosServiceI {
    Host findByHostId(String id);

    Host findById(String id);
}
