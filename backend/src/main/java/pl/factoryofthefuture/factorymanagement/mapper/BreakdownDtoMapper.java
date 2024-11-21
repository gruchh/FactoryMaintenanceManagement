package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
@AllArgsConstructor
public class BreakdownDtoMapper implements ApplicationContextAware {

    private static MachineService machineService;
    private static EmployeeService employeeService;

    public static List<BreakdownDto> mapToBreakdownDtos(List<Breakdown> breakdowns) {
        return breakdowns.stream()
                .map(BreakdownDtoMapper::mapToBreakdownDto)
                .collect(Collectors.toList());
    }

    public static BreakdownDto mapToBreakdownDto(Breakdown breakdown) {
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

    public static Breakdown mapDtoToBreakdown(BreakdownDto breakdownDto) {
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        BreakdownDtoMapper.machineService = applicationContext.getBean(MachineService.class);
        BreakdownDtoMapper.employeeService = applicationContext.getBean(EmployeeService.class);
    }
}
