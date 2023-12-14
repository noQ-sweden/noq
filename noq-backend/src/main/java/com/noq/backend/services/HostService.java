package com.noq.backend.services;

import com.noq.backend.exceptions.BookingException;
import com.noq.backend.exceptions.HostNotFoundException;
import com.noq.backend.models.Booking;
import com.noq.backend.models.Host;
import com.noq.backend.repositories.BookingRepository;
import com.noq.backend.repositories.HostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class HostService {
    private final HostRepository hostRepository;
    private final BookingRepository bookingRepository;

    public Optional<Host> create(Host host) {
        // TODO Implement this when you create the Frontend for the same
        throw new RuntimeException("HostService.create :: Not Implemented");
    }

    public List<Host> findByHostCity(String city) {
        log.info("Looking for Hosts in city: {}", city);
        return hostRepository.getAllByCity(city.toLowerCase());
    }

    public List<Host> allHosts() {
        List<Host> hosts = hostRepository.findAll();
        log.info("Found {} Hosts in total: ", hosts);
        return hosts;
    }

    public List<Host> allHostsAvailable(String userId) {
        List<Booking> allByUserId = bookingRepository.findAllByUserId(UUID.fromString(userId));
        return hostRepository.findAll()
                .stream()
                .filter(host -> allByUserId.stream().noneMatch(booking -> booking.getHostId().equals(host.getHostId())))
                .collect(Collectors.toList());
    }

    public Host findHostById(UUID id) {
        return hostRepository.findById(id)
                .orElseThrow( () -> new HostNotFoundException(id.toString()));
    }
}
