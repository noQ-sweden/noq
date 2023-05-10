package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="hosts")
public class Host {

    @Id @Column(name = "id", nullable = false)
    private UUID hostId;
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Address address;
    private String image;
    private Long beds;

    // Denna har jag kommenterat ut för att få Kevins interface att fungera, i JIRA säger model att beds ska vara ett nummer.
    //@OneToMany(mappedBy = "host",  cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<Bed> beds = new HashSet<>();


}
