package pl.factoryofthefuture.factorymanagement.Entity;

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
    private String fullName;
    private String shortCut;
    private LocalDate creationDate;
    @OneToMany
    private Set<Machine> machineSet;
}
