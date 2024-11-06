package pl.factoryofthefuture.factorymanagement.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;

import java.util.List;
import java.util.Optional;

@Repository
public interface BreakdownRepository extends JpaRepository<Breakdown, Long> {

    @Query("SELECT b from BREAKDOWNS b LEFT JOIN FETCH b.employeeSet")
    List<Breakdown> findAllBreakdowns(Pageable page);

    @EntityGraph(attributePaths = {"machine", "employeeSet"})
    List<Breakdown> findAll();

    @EntityGraph(attributePaths = {"machine", "employeeSet"})
    Optional<Breakdown> findById(Long id);
}
