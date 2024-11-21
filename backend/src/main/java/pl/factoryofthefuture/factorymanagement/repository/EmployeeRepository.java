package pl.factoryofthefuture.factorymanagement.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @EntityGraph(attributePaths = {"breakdownsSet"})
    List<Employee> findAll();

    @EntityGraph(attributePaths = {"breakdownsSet"})
    Optional<Employee> findById(Long id);
}
