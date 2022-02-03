package pl.edu.zut.gk45098backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.zut.gk45098backend.model.ProjectLayer;

import java.util.Optional;

public interface ProjectLayerRepository extends JpaRepository<ProjectLayer, Long> {
}
