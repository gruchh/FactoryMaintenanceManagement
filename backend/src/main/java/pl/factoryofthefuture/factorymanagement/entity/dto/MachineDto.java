package pl.factoryofthefuture.factorymanagement.entity.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MachineDto {
    private long id;
    private String name;
    private String manufacturer;
    private LocalDate productionDate;
    private LocalDate lastMaintenanceDate;
    private double energyConsumption;
    private long departmentId;
}
