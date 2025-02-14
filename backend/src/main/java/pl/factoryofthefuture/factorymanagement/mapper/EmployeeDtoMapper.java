package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.entity.dto.EmployeeDto;
import pl.factoryofthefuture.factorymanagement.service.BreakdownService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class EmployeeDtoMapper {

    private final BreakdownService breakdownService;

    public EmployeeDto mapEmployeeToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .jobPosition(employee.getJobPosition())
                .dateOfBirth(employee.getDateOfBirth())
                .hireDate(employee.getHireDate())
                .shift(employee.getShift())
                .contactNumber(employee.getContactNumber())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .performanceRating(employee.getPerformanceRating())
                .assessmentDate(employee.getAssesmentDate())
                .breakdownIds(employee.getBreakdownsSet().stream().map(Breakdown::getId).collect(Collectors.toSet()))
                .build();
    }

    public List<EmployeeDto> mapEmployeesToDtos(List<Employee> employees) {
        return employees.stream()
                .map(this::mapEmployeeToDto)
                .collect(Collectors.toList());
    }

    public Employee mapEmployeeDtoToEntity(EmployeeDto employeeDto) {
        Set<Breakdown> breakdownSet = breakdownService.findBreakdownsById(employeeDto.getBreakdownIds());
        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .surname(employeeDto.getSurname())
                .jobPosition(employeeDto.getJobPosition())
                .dateOfBirth(employeeDto.getDateOfBirth())
                .hireDate(employeeDto.getHireDate())
                .shift(employeeDto.getShift())
                .contactNumber(employeeDto.getContactNumber())
                .email(employeeDto.getEmail())
                .salary(employeeDto.getSalary())
                .performanceRating(employeeDto.getPerformanceRating())
                .assesmentDate(employeeDto.getAssessmentDate())
                .breakdownsSet(breakdownSet)
                .build();
    }
}