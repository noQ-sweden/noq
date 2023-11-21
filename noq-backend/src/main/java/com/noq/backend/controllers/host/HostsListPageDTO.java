package com.noq.backend.controllers.host;

import com.noq.backend.models.Host;

import java.util.List;

record HostsListPageDTO(List<Host> availableHosts) {
}
