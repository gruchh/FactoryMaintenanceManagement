package pl.factoryofthefuture.factorymanagement.mapper;

import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.entity.dto.DepartmentDto;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentDtoMapper {

    public static List<DepartmentDto> mapToDepartmentDtos(List<Department> departments) {

        return departments.stream()
                .map(DepartmentDtoMapper::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    public static DepartmentDto mapToDepartmentDto (Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .fullName(department.getFullName())
                .shortCut(department.getShortCut())
                .creationDate(department.getCreationDate())
                .build();
    }

}
