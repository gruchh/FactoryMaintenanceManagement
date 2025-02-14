package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.repository.EmployeeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such element " + id));
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + employee.getId()));
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setSurname(employee.getSurname());
        updatedEmployee.setJobPosition(employee.getJobPosition());
        updatedEmployee.setDateOfBirth(employee.getDateOfBirth());
        updatedEmployee.setHireDate(employee.getHireDate());
        updatedEmployee.setShift(employee.getShift());
        updatedEmployee.setContactNumber(employee.getContactNumber());
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setSalary(employee.getSalary());
        updatedEmployee.setPerformanceRating(employee.getPerformanceRating());
        updatedEmployee.setBreakdownsSet(employee.getBreakdownsSet());
        return employeeRepository.save(updatedEmployee);
    }

    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }
}
