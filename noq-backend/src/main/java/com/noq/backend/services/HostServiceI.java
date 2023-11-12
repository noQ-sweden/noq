package com.noq.backend.services;

import com.noq.backend.models.Host;

public interface HostServiceI {
    Host findByHostId(String id);

    Host findById(String id);
}
