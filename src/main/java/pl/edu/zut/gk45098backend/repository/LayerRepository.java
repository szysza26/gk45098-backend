package pl.edu.zut.gk45098backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.zut.gk45098backend.model.Layer;
import pl.edu.zut.gk45098backend.model.User;

import java.util.List;
import java.util.Optional;

public interface LayerRepository extends JpaRepository<Layer, Long> {
    public List<Layer> findAllByUser(User user);
    public Optional<Layer> findByIdAndUser(Long id, User user);
}
