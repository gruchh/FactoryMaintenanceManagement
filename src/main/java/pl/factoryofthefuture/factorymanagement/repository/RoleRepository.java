package pl.factoryofthefuture.factorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.factoryofthefuture.factorymanagement.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
