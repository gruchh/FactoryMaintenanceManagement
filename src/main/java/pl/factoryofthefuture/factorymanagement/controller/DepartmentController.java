package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.entity.dto.DepartmentDto;
import pl.factoryofthefuture.factorymanagement.service.DepartmantService;

import java.util.List;

import static pl.factoryofthefuture.factorymanagement.mapper.DepartmentDtoMapper.mapToDepartmentDto;
import static pl.factoryofthefuture.factorymanagement.mapper.DepartmentDtoMapper.mapToDepartmentDtos;

@RestController()
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmantService departmantService;

    @GetMapping()
    public List<DepartmentDto> getDepartments() {
        return mapToDepartmentDtos(departmantService.getDepartments());
    }

    @GetMapping("/{id}")
    public DepartmentDto getDepartment(@PathVariable Long id) {
        return mapToDepartmentDto(departmantService.getDepartment(id));
    }


}
