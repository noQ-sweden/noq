package com.noq.backend.repository;

import com.noq.backend.models.Bed;
import com.noq.backend.models.Host;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;

@Repository
public class BedRepositoryImpl implements BedRepository {
    private final Map<String, Bed> beds = new HashMap<>();

    @Override
    public Bed save(Bed bed) {
        beds.put(bed.getId(), bed);
        return bed;
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
