package pl.edu.zut.gk45098backend.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.zut.gk45098backend.model.Property;

public interface PropertyRepository extends CrudRepository<Property, Long> {
}
