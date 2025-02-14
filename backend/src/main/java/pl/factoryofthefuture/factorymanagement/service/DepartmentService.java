package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.exception.NotFoundException;
import pl.factoryofthefuture.factorymanagement.repository.DepartmentRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department department) {
        Department updatedDepartment = departmentRepository.findById(department.getId())
                .orElseThrow(() -> new NotFoundException(department.getId()));
        updatedDepartment.setFullName(department.getFullName());
        updatedDepartment.setShortCut(department.getShortCut());
        updatedDepartment.setCreationDate(department.getCreationDate());
        updatedDepartment.setMachineSet(department.getMachineSet());
        return departmentRepository.save(updatedDepartment);
    }

    @Transactional
    public void deleteById(long id) {
        if (!departmentRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        departmentRepository.deleteById(id);
    }
}