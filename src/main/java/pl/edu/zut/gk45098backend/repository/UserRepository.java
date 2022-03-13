package pl.edu.zut.gk45098backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.zut.gk45098backend.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> getUserByEmail(String email);
}
