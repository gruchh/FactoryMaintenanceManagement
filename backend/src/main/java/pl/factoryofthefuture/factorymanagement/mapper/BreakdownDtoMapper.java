package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDto;
import pl.factoryofthefuture.factorymanagement.service.EmployeeService;
import pl.factoryofthefuture.factorymanagement.service.MachineService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class BreakdownDtoMapper {

    private final MachineService machineService;
    private final EmployeeService employeeService;

    public List<BreakdownDto> mapBreakdownsToDtos(List<Breakdown> breakdowns) {
        return breakdowns.stream()
                .map(this::mapBreakdownToDtos)
                .collect(Collectors.toList());
    }

    public BreakdownDto mapBreakdownToDtos(Breakdown breakdown) {
        return BreakdownDto.builder()
                .id(breakdown.getId())
                .eventDescription(breakdown.getEventDescription())
                .startDate(breakdown.getStartDate())
                .endDate(breakdown.getEndDate())
                .severity(breakdown.getSeverity())
                .cause(breakdown.getCause())
                .usedParts(breakdown.getUsedParts())
                .comments(breakdown.getComments())
                .machineId(breakdown.getMachine().getId())
                .employeeIds(breakdown.getEmployeeSet().stream().map(Employee::getId).collect(Collectors.toSet()))
                .build();
    }

    public Breakdown mapBreakdownDtoToEntity(BreakdownDto breakdownDto) {
        Machine machine = machineService.getMachine(breakdownDto.getMachineId());
        Set<Employee> employeeSet = breakdownDto.getEmployeeIds().stream()
                .map(employeeService::getEmployee)
                .collect(Collectors.toSet());

        return Breakdown.builder()
                .id(breakdownDto.getId())
                .eventDescription(breakdownDto.getEventDescription())
                .startDate(breakdownDto.getStartDate())
                .endDate(breakdownDto.getEndDate())
                .severity(breakdownDto.getSeverity())
                .cause(breakdownDto.getCause())
                .usedParts(breakdownDto.getUsedParts())
                .comments(breakdownDto.getComments())
                .machine(machine)
                .employeeSet(employeeSet)
                .build();
    }
}
