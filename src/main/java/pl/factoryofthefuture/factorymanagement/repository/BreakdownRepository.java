package pl.factoryofthefuture.factorymanagement.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;

import java.util.List;

@Repository
public interface BreakdownRepository extends JpaRepository<Breakdown, Long> {

    @Query("SELECT b from BREAKDOWNS b")
    List<Breakdown> findAllBreakdowns(Pageable page);
}
