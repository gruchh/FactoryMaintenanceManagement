package pl.factoryofthefuture.factorymanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
