package pl.factoryofthefuture.factorymanagement.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
