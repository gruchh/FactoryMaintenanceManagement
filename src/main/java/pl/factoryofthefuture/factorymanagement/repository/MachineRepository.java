package pl.factoryofthefuture.factorymanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.entity.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
}
