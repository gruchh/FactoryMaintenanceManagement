package pl.factoryofthefuture.factorymanagement.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.Model.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
}
