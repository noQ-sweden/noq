package com.noq.backend.clients.user.availableHosts;

import java.util.List;

record UserAvailableHostsDTO(
        List<HostDTO> availableHosts
) {
}
