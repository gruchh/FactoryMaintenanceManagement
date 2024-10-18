package pl.factoryofthefuture.factorymanagement.mapper;

import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.entity.dto.EmployeeDto;

import java.util.List;
import java.util.stream.Collectors;


public class EmployeeDtoMapper {

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
                .breakdownIds(employee.getBreakdowns().stream().map(Breakdown::getId).collect(Collectors.toSet()))
                .build();
    }

}
