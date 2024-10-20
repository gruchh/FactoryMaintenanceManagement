package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
