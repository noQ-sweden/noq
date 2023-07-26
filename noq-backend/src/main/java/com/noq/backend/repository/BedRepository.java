package com.noq.backend.repository;

import com.noq.backend.models.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BedRepository {

    Bed save (Bed bed);

    Bed getBedbyBedId(String bedId);

    List<Bed> getBedsByHostId(String hostId);

}
