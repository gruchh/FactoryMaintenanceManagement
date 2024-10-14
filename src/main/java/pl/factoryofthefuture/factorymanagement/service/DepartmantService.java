package pl.factoryofthefuture.factorymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.Department;
import pl.factoryofthefuture.factorymanagement.repository.DepartmentRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DepartmantService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such element " + id));
    }


}
