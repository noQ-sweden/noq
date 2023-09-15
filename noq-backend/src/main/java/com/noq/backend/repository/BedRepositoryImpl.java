package com.noq.backend.repository;

import com.noq.backend.models.Bed;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BedRepositoryImpl implements BedRepository {
    private final Map<String, Bed> beds = new HashMap<>();

    @Override
    public Bed save(Bed bed) {
        beds.put(bed.getId(), bed);
        return bed;
    }

    @Override
    public void saveAll(List<Bed> bedsList) {
        for (Bed bed : bedsList) {
            beds.put(bed.getId(), bed);
        }
    }

    @Override
    public Bed getBedbyBedId(String bedId) {
        return beds.get(bedId);
    }

    @Override
    public List<Bed> getBedsByHostId(String hostId) {
        List<Bed> bedsForHost = new ArrayList<>();

        for (Bed bed : beds.values()) {
            if (bed.getHost() != null && bed.getHost().getHostId().equals(hostId)) {
                bedsForHost.add(bed);
            }
        }
        return bedsForHost;
    }

}
