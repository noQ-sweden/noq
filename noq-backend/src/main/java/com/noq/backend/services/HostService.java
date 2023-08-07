package com.noq.backend.services;

import com.noq.backend.models.Address;
import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import com.noq.backend.repository.BedRepository;
import com.noq.backend.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HostService {

    private HostRepository hostRepository;
    private BedRepository bedRepository;

    @Autowired
    public HostService(HostRepository hostRepository, BedRepository bedRepository) {
        this.hostRepository = hostRepository;
        this.bedRepository = bedRepository;
    }

    public Host getHostById(String id){
        return hostRepository.getHostByHostId(id);
    }

    public List<Host> getAllHosts() {
        return hostRepository.getAllHosts();
    }

    public List<Host> createHosts() {

        /* skapar  3 st hosts, host3 har två sämngar, host4 en säng, och host5 ingen säng */

        Host host3 = new Host("host3", "Test-Härberget 3", new Address(UUID.randomUUID().toString(), "Gatgatan", "12", "12345", "Stockholm"), "url/till/bild/pa/Harberget1.png", new ArrayList<>());
        Host host4 = new Host("host4", "Test-Härberget 4", new Address(UUID.randomUUID().toString(), "Vägvägen", "21", "23546", "Lund"), "url/till/bild/pa/Harberget2.png", new ArrayList<>());
        Host host5 = new Host("host5", "Test-Härberget 5", new Address(UUID.randomUUID().toString(), "Vägvägen", "21", "23546", "Lund"), "url/till/bild/pa/Harberget2.png", new ArrayList<>());


        hostRepository.save(host3);
        hostRepository.save(host4);
        hostRepository.save(host5);

        Bed bed1 = new Bed("bed1", host3);
        Bed bed2 = new Bed("bed2", host3);
        Bed bed3 = new Bed("bed3", host4);

        /* beds har host som null men de är kopplade ???  */

        host3.addBed(bed1);
        host3.addBed(bed2);
        host4.addBed(bed3);

        bedRepository.save(bed1);
        bedRepository.save(bed2);
        bedRepository.save(bed3);

        hostRepository.save(host3);
        hostRepository.save(host4);

        return new ArrayList<>(hostRepository.getAllHosts());
    }

    public List<Host> getAllHostsWithBeds() {
        return hostRepository.getAllHosts()
                .stream()
                .filter(host -> !host.getBeds().isEmpty())
                .collect(Collectors.toList());
    }

    public Host createBeds(String hostId, int numberOfBeds) {
        Host host = hostRepository.getHostByHostId(hostId);

        if (host != null) {
            List<Bed> beds = IntStream.range(0, numberOfBeds)
                    .mapToObj(i -> new Bed("newBed" + (i + 1), host))
                    .collect(Collectors.toList());

            host.getBeds().addAll(beds);
            bedRepository.saveAll(beds);
            hostRepository.save(host);
        } else {
            System.out.println("Host not found for hostId: " + hostId);
        }

        return host;
    }



}

