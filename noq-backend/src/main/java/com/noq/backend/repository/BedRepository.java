package com.noq.backend.repository;

import com.noq.backend.models.cosmos.Bed;

import java.util.List;

public interface BedRepository {

    Bed save (Bed bed);

    Bed getBedbyBedId(String bedId);

    List<Bed> getBedsByHostId(String hostId);

    void saveAll(List<Bed> beds);
}
