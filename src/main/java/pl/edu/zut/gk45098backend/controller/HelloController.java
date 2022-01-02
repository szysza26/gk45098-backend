package pl.edu.zut.gk45098backend.controller;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.zut.gk45098backend.model.Feature;
import pl.edu.zut.gk45098backend.repository.FeatureRepository;

import java.util.List;

@RequestMapping("/api")
@RestController
public class HelloController {

    private FeatureRepository featureRepository;

    public HelloController(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @GetMapping("/features")
    public List<Feature> index() {
        return featureRepository.findAll();
    }

    @PostMapping("/features")
    public String create() {
        Feature feature = new Feature();
        feature.setName("name");

        GeometryFactory gf = new GeometryFactory();
        Point point = gf.createPoint(new Coordinate(1.2345678, 2.3456789));
        feature.setGeometry(point);

        featureRepository.save(feature);

        return "ok";
    }

}
