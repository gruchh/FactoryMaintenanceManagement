package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.entity.dto.DepartmentDto;
import pl.factoryofthefuture.factorymanagement.service.DepartmentService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.DepartmentDtoMapper.*;

@RestController()
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmantService;

    @GetMapping()
    public List<DepartmentDto> getDepartments() {
        return mapToDepartmentDtos(departmantService.getDepartments());
    }

    @GetMapping("/{id}")
    public DepartmentDto getDepartment(@PathVariable long id) {
        return mapToDepartmentDto(departmantService.getDepartment(id));
    }

    @PostMapping()
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        Department savedDepartment = departmantService.saveDepartment(mapDtoToDepartment(departmentDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDepartmentDto(savedDepartment));
    }

    @PutMapping()
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto) {
        Department editedDepartment = departmantService.updateDepartment(mapDtoToDepartment(departmentDto));
        return ResponseEntity.status(HttpStatus.OK).body(mapToDepartmentDto(editedDepartment));
    }

    @DeleteMapping("/{id}")
    public void deleteBudget(@PathVariable long id) {
        departmantService.deleteById(id);
    }
}
