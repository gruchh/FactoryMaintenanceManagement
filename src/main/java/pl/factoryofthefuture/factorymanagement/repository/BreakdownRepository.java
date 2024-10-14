package pl.factoryofthefuture.factorymanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.entity.Breakdown;

@Repository
public interface BreakdownRepository extends JpaRepository<Breakdown, Long> {
}
