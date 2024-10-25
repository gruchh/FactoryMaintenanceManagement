package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.Budget;
import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.repository.DepartmentRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class DepartmantService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such element " + id));
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department department) {
        Department updatedDepartment = departmentRepository.findById(department.getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found with id: " + department.getId()));
        updatedDepartment.setId(updatedDepartment.getId());

        return departmentRepository.save(updatedDepartment);
    }

    public void deleteById(long id) {
        departmentRepository.deleteById(id);
    }
}
