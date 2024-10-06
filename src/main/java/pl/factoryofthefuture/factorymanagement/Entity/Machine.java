package pl.factoryofthefuture.factorymanagement.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity (name="MACHINES")
@NoArgsConstructor
@Getter
@Setter
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String manufacturer;
    private LocalDate productionDate;
    private LocalDate lastMaintenanceDate;
    private double energyConsumption;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}

