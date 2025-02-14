package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.entity.dto.EmployeeDto;
import pl.factoryofthefuture.factorymanagement.mapper.EmployeeDtoMapper;
import pl.factoryofthefuture.factorymanagement.service.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController()
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeDtoMapper employeeDtoMapper;

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        try {
            List<EmployeeDto> employeeDtos = employeeDtoMapper.mapEmployeesToDtos(employeeService.getEmployees());
            return ResponseEntity.ok(employeeDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable long id) {
        try {
            Employee employee = employeeService.getEmployee(id);
            if (employee != null) {
                return ResponseEntity.ok(employeeDtoMapper.mapEmployeeToDto(employee));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            Employee savedEmployee = employeeService.saveEmployee(employeeDtoMapper.mapEmployeeDtoToEntity(employeeDto));
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeDtoMapper.mapEmployeeToDto(savedEmployee));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping()
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            Employee editedEmployee = employeeService.updateEmployee(employeeDtoMapper.mapEmployeeDtoToEntity(employeeDto));
            return ResponseEntity.ok(employeeDtoMapper.mapEmployeeToDto(editedEmployee));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
        try {
            employeeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
