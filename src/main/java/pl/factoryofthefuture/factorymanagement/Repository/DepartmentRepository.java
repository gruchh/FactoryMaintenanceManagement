package pl.factoryofthefuture.factorymanagement.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.Entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
