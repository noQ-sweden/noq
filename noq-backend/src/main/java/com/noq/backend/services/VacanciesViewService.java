//package com.noq.backend.services;
//
//import com.noq.backend.models.cosmos.Bed;
//import com.noq.backend.models.Host;
//import com.noq.backend.models.Vacancy;
//import com.noq.backend.repository.HostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class VacanciesViewService {
//
//    private final HostRepository hostRepository;
//
//    @Autowired
//    public VacanciesViewService(HostRepository hostRepository) {
//        this.hostRepository = hostRepository;
//    }
//
//    public List<Vacancy> getAllVacancies() {
//
//        List<Host> hostsWithBeds = hostRepository.getAllHosts().stream()
//                .filter(host -> !host.getBeds().isEmpty())
//                .collect(Collectors.toList());
//
//        Map<String, Vacancy> vacancyMap = new HashMap<>();
//
//        for (Host host : hostsWithBeds) {
//            boolean vacancyAdded = false;
//
//            for (Bed bed : host.getBeds()) {
//                if (!bed.getReserved() && !vacancyAdded) {
//                    Vacancy vacancy = new Vacancy();
//                    vacancy.setHostId(host.getHostId());
//                    vacancy.setHostName(host.getName());
//                    vacancy.setAddress(host.getAddress());
//                    vacancy.setHostImg(host.getImage());
//                    vacancy.setBedId(bed.getBedId());
//
//                    vacancyMap.put(host.getHostId(), vacancy);
//                    vacancyAdded = true;
//                }
//            }
//        }
//
//        List<Vacancy> vacancies = new ArrayList<>(vacancyMap.values());
//
//        return vacancies;
//    }
//}