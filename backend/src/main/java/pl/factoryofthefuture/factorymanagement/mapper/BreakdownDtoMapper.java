package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDetailsDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownListItemDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.BreakdownWithShortCutDto;
import pl.factoryofthefuture.factorymanagement.entity.projections.BreakdownWithShortCutProjection;
import pl.factoryofthefuture.factorymanagement.service.EmployeeService;
import pl.factoryofthefuture.factorymanagement.service.MachineService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class BreakdownDtoMapper {

    private final EmployeeDtoMapper employeeDtoMapper;
    private final MachineDtoMapper machineDtoMapper;

    public BreakdownListItemDto mapBreakdownToListItemDto(Breakdown breakdown) {
        return BreakdownListItemDto.builder()
                .id(breakdown.getId())
                .eventDescription(breakdown.getEventDescription())
                .startDate(breakdown.getStartDate())
                .endDate(breakdown.getEndDate())
                .severity(breakdown.getSeverity())
                .machineId(breakdown.getMachine().getId())
                .build();
    }

    public List<BreakdownListItemDto> mapBreakdownsToListItemDtos(List<Breakdown> breakdowns) {
        return breakdowns.stream()
                .map(this::mapBreakdownToListItemDto)
                .collect(Collectors.toList());
    }

    public BreakdownDetailsDto mapBreakdownToDetailsDto(Breakdown breakdown) {
        return BreakdownDetailsDto.builder()
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

    public List<BreakdownDto> mapBreakdownsToDtos(List<Breakdown> breakdowns) {
        return breakdowns.stream()
                .map(this::mapBreakdownToDto)
                .collect(Collectors.toList());
    }

    public BreakdownDto mapBreakdownToDto(Breakdown breakdown) {
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

    public Breakdown mapBreakdownDtoToEntity(BreakdownDto breakdownDto, Machine machine, Set<Employee> employeeSet) {
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

    public BreakdownWithShortCutDto mapBreakdownProjectionToDto(BreakdownWithShortCutProjection breakdownWithShortuctProjection) {
        return BreakdownWithShortCutDto.builder()
                .id(breakdownWithShortuctProjection.getId())
                .startDate(breakdownWithShortuctProjection.getStartDate())
                .endDate(breakdownWithShortuctProjection.getEndDate())
                .severity(breakdownWithShortuctProjection.getSeverity())
                .shortCut(breakdownWithShortuctProjection.getShortCut())
                .build();
    }

    public List<BreakdownWithShortCutDto> mapBreakdownProjectionToDtos(List<BreakdownWithShortCutProjection> breakdownWithShortuctProjectionList) {
        return breakdownWithShortuctProjectionList.stream()
                .map(this::mapBreakdownProjectionToDto)
                .collect(Collectors.toList());
    }
}