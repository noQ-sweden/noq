package com.noq.backend.models;
import jakarta.persistence.Column;
import lombok.*;
import jakarta.persistence.Id;
import java.util.UUID;
import jakarta.persistence.*;

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
