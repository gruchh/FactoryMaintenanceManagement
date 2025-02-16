package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.entity.Machine;
import pl.factoryofthefuture.factorymanagement.entity.dto.DepartmentDto;
import pl.factoryofthefuture.factorymanagement.exception.NotFoundException;
import pl.factoryofthefuture.factorymanagement.mapper.DepartmentDtoMapper;
import pl.factoryofthefuture.factorymanagement.repository.DepartmentRepository;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentDtoMapper departmentDtoMapper;
    private final MachineService machineService;

    public List<DepartmentDto> getAllDepartmentsDtos() {
        List<Department> allDepartments = departmentRepository.findAll();
        return departmentDtoMapper.mapDepartmentsToDtos(allDepartments);
    }

    public DepartmentDto getDepartmentDtoById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return departmentDtoMapper.mapDepartmentToDto(department);
    }

    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = departmentDtoMapper.mapDepartmentDtoToEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return departmentDtoMapper.mapDepartmentToDto(savedDepartment);
    }

    @Transactional
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department updatedDepartment = departmentRepository.findById(departmentDto.getId())
                .orElseThrow(() -> new NotFoundException(departmentDto.getId()));
        updatedDepartment.setFullName(departmentDto.getFullName());
        updatedDepartment.setShortCut(departmentDto.getShortCut());
        if (departmentDto.getMachineIds() != null && !departmentDto.getMachineIds().isEmpty()) {
            Set<Machine> machineSet = machineService.getMachinesByIds(departmentDto.getMachineIds());
            updatedDepartment.setMachineSet(machineSet);
        } else {
            updatedDepartment.setMachineSet(null);
        }
        Department savedDepartment = departmentRepository.save(updatedDepartment);
        return departmentDtoMapper.mapDepartmentToDto(savedDepartment);
    }

    @Transactional
    public void deleteById(long id) {
        if (!departmentRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        departmentRepository.deleteById(id);
    }
}