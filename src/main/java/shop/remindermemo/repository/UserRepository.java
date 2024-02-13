package shop.remindermemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.remindermemo.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
