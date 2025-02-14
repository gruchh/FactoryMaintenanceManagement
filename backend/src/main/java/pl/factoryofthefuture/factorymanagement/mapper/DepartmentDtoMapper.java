package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.DepartmentDto;
import pl.factoryofthefuture.factorymanagement.service.MachineService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class DepartmentDtoMapper {

    private final MachineService machineService;

    public List<DepartmentDto> mapDepartmentDtosToEntities(List<Department> departments) {
        return departments.stream()
                .map(this::mapDepartmentToDto)
                .collect(Collectors.toList());
    }

    public DepartmentDto mapDepartmentToDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .fullName(department.getFullName())
                .shortCut(department.getShortCut())
                .creationDate(department.getCreationDate())
                .machineIds(department.getMachineSet().stream().map(Machine::getId).collect(Collectors.toSet()))
                .build();
    }

    public Department mapDepartmentDtoToEntity(DepartmentDto departmentDto) {
        Set<Machine> machineSet = machineService.getMachinesByIds(departmentDto.getMachineIds());
        return Department.builder()
                .fullName(departmentDto.getFullName())
                .shortCut(departmentDto.getShortCut())
                .creationDate(departmentDto.getCreationDate())
                .machineSet(machineSet)
                .build();
    }
}
