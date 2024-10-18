package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.factoryofthefuture.factorymanagement.entity.Employee;
import pl.factoryofthefuture.factorymanagement.entity.dto.EmployeeDto;
import pl.factoryofthefuture.factorymanagement.service.EmployeeService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.EmployeeDtoMapper.mapToEmployeeDto;
import static pl.factoryofthefuture.factorymanagement.mapper.EmployeeDtoMapper.mapToEmployeeDtos;

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


}
