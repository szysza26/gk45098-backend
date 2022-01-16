package pl.edu.zut.gk45098backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.zut.gk45098backend.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
