package com.noq.backend.repository;

import com.noq.backend.models.Host;
import com.noq.backend.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class HostRepositoryImpl implements HostRepository {

    private final Map<String, Host> hosts = new HashMap<>();
    @Override
    public Host save(Host host) {
        hosts.put(host.getHostId(), host);
        return host;
    }

    @Override
    public Host getHostByHostId(String hostId) {
        return hosts.get(hostId);
    }

    @Override
    public List<Host> getAllHosts() {
        return new ArrayList<>(hosts.values());
    }
}
