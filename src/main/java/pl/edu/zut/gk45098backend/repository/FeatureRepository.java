package pl.edu.zut.gk45098backend.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.zut.gk45098backend.model.Feature;

import java.util.List;

public interface FeatureRepository extends CrudRepository<Feature, Long> {
}
