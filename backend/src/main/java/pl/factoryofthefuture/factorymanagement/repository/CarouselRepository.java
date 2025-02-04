package pl.factoryofthefuture.factorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.entity.CarouselItem;

import java.util.List;

@Repository
public interface CarouselRepository extends JpaRepository<CarouselItem, Long> {

    List<CarouselItem> findByIsVisibleTrue();
}
