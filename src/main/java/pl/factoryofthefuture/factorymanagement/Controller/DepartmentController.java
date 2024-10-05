package pl.factoryofthefuture.factorymanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.factoryofthefuture.factorymanagement.Model.Department;
import pl.factoryofthefuture.factorymanagement.Service.DepartmantService;

import java.util.List;

@RestController()
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    DepartmantService departmantService;

    @GetMapping()
    public List<Department> getDepartments() {
        return departmantService.getDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable Long id) {
        return departmantService.getDepartment(id);
    }


}
