package pl.factoryofthefuture.factorymanagement.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;
import pl.factoryofthefuture.factorymanagement.entity.projections.BreakdownWithShortCutProjection;

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

    @Query("SELECT new pl.factoryofthefuture.factorymanagement.entity.projections.BreakdownWithShortCutProjection(" +
            "b.id," +
            "b.eventDescription," +
            "b.startDate," +
            "b.endDate," +
            "b.severity," +
            "b.cause," +
            "b.usedParts," +
            "b.comments," +
            "d.shortCut)" +
            "FROM BREAKDOWNS b " +
            "JOIN b.machine m " +
            "JOIN m.department d")
    List<BreakdownWithShortCutProjection> findAllBreakdownsWithShortCut();
}