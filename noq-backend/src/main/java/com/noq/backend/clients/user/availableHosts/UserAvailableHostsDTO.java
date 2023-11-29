package com.noq.backend.clients.user.availableHosts;

import com.noq.backend.clients.user.availableHosts.dto.HostDTO;

import java.util.List;

record UserAvailableHostsDTO(
        List<HostDTO> availableHosts
) {
}
