package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.DepartmentDto;
import pl.factoryofthefuture.factorymanagement.service.MachineService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DepartmentDtoMapper implements ApplicationContextAware {

    private static MachineService machineService;

    public static List<DepartmentDto> mapToDepartmentDtos(List<Department> departments) {
        return departments.stream()
                .map(DepartmentDtoMapper::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    public static DepartmentDto mapToDepartmentDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .fullName(department.getFullName())
                .shortCut(department.getShortCut())
                .creationDate(department.getCreationDate())
                .machineIds(department.getMachineSet().stream().map(Machine::getId).collect(Collectors.toSet()))
                .build();
    }

    public static Department mapDtoToDepartment(DepartmentDto departmentDto) {
        Set<Machine> machineSet = machineService.findMachinesById(departmentDto.getMachineIds());
        return Department.builder()
                .fullName(departmentDto.getFullName())
                .shortCut(departmentDto.getShortCut())
                .creationDate(departmentDto.getCreationDate())
                .machineSet(machineSet)
                .build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        DepartmentDtoMapper.machineService = applicationContext.getBean(MachineService.class);
    }
}
