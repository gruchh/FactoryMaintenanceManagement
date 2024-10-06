package pl.factoryofthefuture.factorymanagement.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.Entity.Breakdown;

@Repository
public interface BreakdownRepository extends JpaRepository<Breakdown, Long> {
}
