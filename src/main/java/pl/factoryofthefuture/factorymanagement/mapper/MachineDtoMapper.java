package pl.factoryofthefuture.factorymanagement.mapper;

import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.MachineDto;

import java.util.List;
import java.util.stream.Collectors;


public class MachineDtoMapper {

    public static List<MachineDto> mapToMachineDtos(List<Machine> machines) {

        return machines.stream()
                .map(MachineDtoMapper::mapToMachineDto)
                .collect(Collectors.toList());
    }

    public static MachineDto mapToMachineDto(Machine machine) {

        return MachineDto.builder()
                .id(machine.getId())
                .name(machine.getName())
                .manufacturer(machine.getManufacturer())
                .productionDate(machine.getProductionDate())
                .lastMaintenanceDate(machine.getLastMaintenanceDate())
                .energyConsumption(machine.getEnergyConsumption())
                .departmentId(machine.getDepartment().getId())
                .build();
    }

}
