package com.noq.backend.models;
import lombok.*;


import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Host {
    /*TODO should be id not hostId?*/
    private String hostId;
    private String name;
    private Address address;
    private String image;
    private List<Bed> beds = new ArrayList<>();

    public void addBed(Bed bed) {
        beds.add(bed);
        bed.setHost(this);
    }
}
