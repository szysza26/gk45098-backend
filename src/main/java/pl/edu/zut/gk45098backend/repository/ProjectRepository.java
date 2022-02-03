package pl.edu.zut.gk45098backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.zut.gk45098backend.model.Project;
import pl.edu.zut.gk45098backend.model.User;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    public List<Project> findAllByUser(User user);
    public Optional<Project> findByIdAndUser(Long id, User user);
}
