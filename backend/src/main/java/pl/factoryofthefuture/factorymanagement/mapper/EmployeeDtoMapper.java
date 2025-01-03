package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.entity.dto.EmployeeDto;
import pl.factoryofthefuture.factorymanagement.service.BreakdownService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EmployeeDtoMapper implements ApplicationContextAware {

    private static BreakdownService breakdownService;

    public static List<EmployeeDto> mapToEmployeeDtos(List<Employee> employees) {

        return employees.stream()
                .map(EmployeeDtoMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
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

    public static Employee mapDtoToEmployee(EmployeeDto employeeDto) {
        Set<Breakdown> breakdownSet = breakdownService.findBreakdownsById(employeeDto.getBreakdownIds());
        return Employee.builder()
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        breakdownService = applicationContext.getBean(BreakdownService.class);
    }
}