package am.azaryan.repository;

import am.azaryan.model.Role;
import am.azaryan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String s);

    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findAllByRole(Role role);

    boolean existsByEmail(String email);

}
