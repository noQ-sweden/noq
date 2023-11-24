package com.noq.backend.controllers.user.availableHosts;

import java.util.List;

record UserAvailableHostsDTO(
        List<HostDTO> availableHosts
) {
}
