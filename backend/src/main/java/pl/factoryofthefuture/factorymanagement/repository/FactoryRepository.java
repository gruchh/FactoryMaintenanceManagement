package pl.factoryofthefuture.factorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.entity.Factory;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, Long> {
}
