package pl.factoryofthefuture.factorymanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.factoryofthefuture.factorymanagement.enums.FactoryStatus;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "FACTORIES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Factory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CITY")
    private String city;

    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private FactoryStatus status;

    @Column(name = "ACTIVITY_SCOPE")
    private String scopeOfActivity;

    @OneToMany(mappedBy = "factory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarModel> models;
}