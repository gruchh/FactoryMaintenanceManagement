package pl.factoryofthefuture.factorymanagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.Model.Department;
import pl.factoryofthefuture.factorymanagement.Repository.DepartmentRepository;

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
