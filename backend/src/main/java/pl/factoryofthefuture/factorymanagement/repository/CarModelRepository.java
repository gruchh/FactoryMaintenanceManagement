package pl.factoryofthefuture.factorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.entity.CarModel;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
}
