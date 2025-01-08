package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.MachineDto;
import pl.factoryofthefuture.factorymanagement.service.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class MachineDtoMapper {

    private final DepartmentService departmantService;

    public List<MachineDto> mapMachinesToDtos(List<Machine> machines) {
        return machines.stream()
                .map(this::mapMachineToDto)
                .collect(Collectors.toList());
    }

    public MachineDto mapMachineToDto(Machine machine) {
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

    public Machine mapDtoToMachine(MachineDto machineDto) {
        Department department = departmantService.getDepartment(machineDto.getDepartmentId());
        return Machine.builder()
                .name(machineDto.getName())
                .manufacturer(machineDto.getManufacturer())
                .productionDate(machineDto.getProductionDate())
                .lastMaintenanceDate(machineDto.getLastMaintenanceDate())
                .energyConsumption(machineDto.getEnergyConsumption())
                .department(department)
                .build();
    }
}
