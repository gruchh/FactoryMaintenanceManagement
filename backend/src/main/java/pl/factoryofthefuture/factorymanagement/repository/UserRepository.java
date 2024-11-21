package pl.factoryofthefuture.factorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.factoryofthefuture.factorymanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
