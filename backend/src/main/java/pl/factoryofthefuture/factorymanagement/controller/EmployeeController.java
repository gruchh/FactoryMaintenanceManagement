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

@RestController()
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeDtoMapper employeeDtoMapper;

    @GetMapping()
    public List<EmployeeDto> getEmployees() {
        return employeeDtoMapper.mapEmployeesToDtos(employeeService.getEmployees());
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployee(@PathVariable long id) {
        return employeeDtoMapper.mapEmployeeToDto(employeeService.getEmployee(id));
    }

    @PostMapping()
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee savedEmployee = employeeService.saveEmployee(employeeDtoMapper.mapEmployeeDtoToEntity(employeeDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDtoMapper.mapEmployeeToDto(savedEmployee));
    }

    @PutMapping()
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee editedEmployee = employeeService.updateEmployee(employeeDtoMapper.mapEmployeeDtoToEntity(employeeDto));
        return ResponseEntity.status(HttpStatus.OK).body(employeeDtoMapper.mapEmployeeToDto(editedEmployee));
    }

    @DeleteMapping("/{id}")
    public void deleteBudget(@PathVariable long id) {
        employeeService.deleteById(id);
    }
}
