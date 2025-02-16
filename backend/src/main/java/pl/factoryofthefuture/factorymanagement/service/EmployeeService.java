package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.entity.dto.EmployeeDto;
import pl.factoryofthefuture.factorymanagement.exception.NotFoundException;
import pl.factoryofthefuture.factorymanagement.mapper.EmployeeDtoMapper;
import pl.factoryofthefuture.factorymanagement.repository.BreakdownRepository;
import pl.factoryofthefuture.factorymanagement.repository.EmployeeRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BreakdownRepository breakdownRepository;
    private final EmployeeDtoMapper employeeDtoMapper;

    public List<EmployeeDto> getAllEmployeesDtos() {
        List<Employee> allEmployees = employeeRepository.findAll();
        return employeeDtoMapper.mapEmployeesToDtos(allEmployees);
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public EmployeeDto getEmployeeDto(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return employeeDtoMapper.mapEmployeeToDto(employee);
    }

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Set<Breakdown> breakdownSet = null;
        if (employeeDto.getBreakdownIds() != null && !employeeDto.getBreakdownIds().isEmpty()) {
            breakdownSet = new HashSet<>(breakdownRepository.findAllById(employeeDto.getBreakdownIds()));
        }
        Employee employee = employeeDtoMapper.mapEmployeeDtoToEntity(employeeDto, breakdownSet);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeDtoMapper.mapEmployeeToDto(savedEmployee);
    }

    @Transactional
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee updatedEmployee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new NotFoundException(employeeDto.getId()));
        updatedEmployee.setName(employeeDto.getName());
        updatedEmployee.setSurname(employeeDto.getSurname());
        updatedEmployee.setJobPosition(employeeDto.getJobPosition());
        updatedEmployee.setDateOfBirth(employeeDto.getDateOfBirth());
        updatedEmployee.setHireDate(employeeDto.getHireDate());
        updatedEmployee.setShift(employeeDto.getShift());
        updatedEmployee.setContactNumber(employeeDto.getContactNumber());
        updatedEmployee.setEmail(employeeDto.getEmail());
        updatedEmployee.setSalary(employeeDto.getSalary());
        updatedEmployee.setPerformanceRating(employeeDto.getPerformanceRating());
        Set<Breakdown> breakdownsSet = Collections.emptySet();
        if (employeeDto.getBreakdownIds() != null && !employeeDto.getBreakdownIds().isEmpty()) {
            breakdownsSet = new HashSet<>(breakdownRepository.findAllById(employeeDto.getBreakdownIds()));
        }
        updatedEmployee.setBreakdownsSet(breakdownsSet);
        return employeeDtoMapper.mapEmployeeToDto(updatedEmployee);
    }

    @Transactional
    public void deleteById(long id) {
        if (!employeeRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }
}