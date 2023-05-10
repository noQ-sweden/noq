package com.noq.backend.services;

import org.springframework.stereotype.Service;
import com.noq.backend.dto.BedDTO;
import com.noq.backend.models.Bed;
import com.noq.backend.repository.BedRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BedService {
    private final BedRepository bedRepository;

    public BedService(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }
/*
    public List<BedDTO> getAllBeds() {
        return bedRepository.findAll().stream().map(this::toBedDTO).collect(Collectors.toList());
    }

    public BedDTO getBedById(Long id) {
        return toBedDTO(getExistingBed(id));
    }

    public BedDTO createBed(BedDTO bedDTO) {
        Bed bed = new Bed();
        bed.setBedId(UUID.randomUUID());
        bed.setPrice(bedDTO.price());
        // add more
        return toBedDTO(bedRepository.save(bed));
    }

    public BedDTO updateBed(Long id, BedDTO bedDTO) {
        Bed existingBed = getExistingBed(id);
        existingBed.setPrice(bedDTO.price());
        // more here
        return toBedDTO(bedRepository.save(existingBed));
    }

    private Bed getExistingBed(Long id) {
        return bedRepository.findById(id).orElseThrow();
    }

    public void deleteBed(Long id) {
        bedRepository.deleteById(id);
    }

    public BedDTO toBedDTO(Bed bed) {
        return new BedDTO(
                bed.getPrice(),
                bed.getHost()
        );
    }*/
}
