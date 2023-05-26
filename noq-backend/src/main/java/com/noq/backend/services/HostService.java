package com.noq.backend.services;

import com.noq.backend.models.Address;
import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import com.noq.backend.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HostService {

    private HostRepository hostRepository;

    @Autowired
    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }


    public List<Host> getAllHosts() {
            return hostRepository.getAllHosts();
        }

    public List<Host> createHosts() {
        Host host1 = new Host("3", "Test-Härberget 1", new Address(UUID.randomUUID().toString(), "Gatgatan", "12", "12345", "Stockholm"), "url/till/bild/pa/Harberget1.png", 15L);
        Host host2 = new Host("4", "Test-Härberget 2", new Address(UUID.randomUUID().toString(), "Vägvägen", "21", "23546", "Lund"), "url/till/bild/pa/Harberget2.png", 20L);
        hostRepository.save(host1);
        hostRepository.save(host2);
        return new ArrayList<>(hostRepository.getAllHosts());
    }

}

