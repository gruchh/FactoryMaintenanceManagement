package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.MachineDto;
import pl.factoryofthefuture.factorymanagement.service.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MachineDtoMapper implements ApplicationContextAware {

    private static DepartmentService departmantService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        departmantService = applicationContext.getBean(DepartmentService.class);
    }


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

    public static Machine mapDtoToMachine(MachineDto machineDto) {
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
