package pl.edu.zut.gk45098backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.zut.gk45098backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User getUserByUsername(String username);
}
