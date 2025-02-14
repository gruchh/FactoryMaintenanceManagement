package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.entity.dto.DepartmentDto;
import pl.factoryofthefuture.factorymanagement.mapper.DepartmentDtoMapper;
import pl.factoryofthefuture.factorymanagement.service.DepartmentService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentDtoMapper departmentDtoMapper;

    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        try {
            List<DepartmentDto> departmentDtos = departmentDtoMapper.mapDepartmentDtosToEntities(departmentService.getDepartments());
            return ResponseEntity.ok(departmentDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable long id) {
        try {
            Department department = departmentService.getDepartment(id);
            if (department != null) {
                return ResponseEntity.ok(departmentDtoMapper.mapDepartmentToDto(department));
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
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        try {
            Department savedDepartment = departmentService.saveDepartment(departmentDtoMapper.mapDepartmentDtoToEntity(departmentDto));
            return ResponseEntity.status(HttpStatus.CREATED).body(departmentDtoMapper.mapDepartmentToDto(savedDepartment));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping()
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto) {
        try {
            Department editedDepartment = departmentService.updateDepartment(departmentDtoMapper.mapDepartmentDtoToEntity(departmentDto));
            return ResponseEntity.ok(departmentDtoMapper.mapDepartmentToDto(editedDepartment));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable long id) {
        try {
            departmentService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
