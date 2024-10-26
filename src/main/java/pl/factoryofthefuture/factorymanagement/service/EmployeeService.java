package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.Department;
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

    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + employee.getId()));
        updatedEmployee.setId(updatedEmployee.getId());

        return employeeRepository.save(updatedEmployee);
    }

    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }
}
