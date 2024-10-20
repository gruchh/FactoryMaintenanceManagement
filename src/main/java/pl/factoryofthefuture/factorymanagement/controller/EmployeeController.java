package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.entity.dto.EmployeeDto;
import pl.factoryofthefuture.factorymanagement.service.EmployeeService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.EmployeeDtoMapper.*;

@RestController()
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping()
    public List<EmployeeDto> getEmployees() {
        return mapToEmployeeDtos(employeeService.getEmployees());
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployee(@PathVariable Long id) {
        return mapToEmployeeDto(employeeService.getEmployee(id));
    }

    @PostMapping()
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee savedEmployee = employeeService.saveEmployee(mapDtoToEmployee(employeeDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToEmployeeDto(savedEmployee));
    }
}
