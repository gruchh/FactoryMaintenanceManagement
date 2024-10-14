package pl.factoryofthefuture.factorymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity (name="DEPARTMENTS")
@NoArgsConstructor
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "SHORT_CUT")
    private String shortCut;
    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;
    @OneToMany
    private Set<Machine> machineSet;
}
