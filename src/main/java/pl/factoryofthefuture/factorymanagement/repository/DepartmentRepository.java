package pl.factoryofthefuture.factorymanagement.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.entity.Department;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @EntityGraph(attributePaths = {"machineSet"})
    List<Department> findAll();

    @EntityGraph(attributePaths = {"machineSet"})
    Optional<Department> findById(Long id);
}
