package pl.factoryofthefuture.factorymanagement.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MANUFACTURER")
    private String manufacturer;

    @Column(name = "PRODUCTION_DATE")
    private LocalDate productionDate;

    @Column(name = "LAST_MAINTENANCE_DATE")
    private LocalDate lastMaintenanceDate;

    @Column(name = "ENERGY_CONSUMPTION")
    private double energyConsumption;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}

