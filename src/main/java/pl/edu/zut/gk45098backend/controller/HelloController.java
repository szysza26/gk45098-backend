package pl.edu.zut.gk45098backend.controller;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.zut.gk45098backend.model.Feature;
import pl.edu.zut.gk45098backend.model.Layer;
import pl.edu.zut.gk45098backend.model.Property;
import pl.edu.zut.gk45098backend.repository.LayerRepository;

import java.util.List;

@RequestMapping("/api")
@RestController
public class HelloController {

    private LayerRepository layerRepository;

    public HelloController(LayerRepository layerRepository) {
        this.layerRepository = layerRepository;
    }

    @GetMapping("/features")
    public List<Layer> index() {
        return (List<Layer>) layerRepository.findAll();
    }

    @PostMapping("/features")
    public String create() {
        Layer layer = new Layer();
        layer.setName("layer1");

        Feature feature = new Feature();

        Property property1 = new Property();
        property1.setKey("name");
        property1.setValue("test1");
        property1.setFeature(feature);
        feature.addProperty(property1);

        Property property2 = new Property();
        property2.setKey("description");
        property2.setValue("qwerty");
        property2.setFeature(feature);
        feature.addProperty(property2);

        GeometryFactory gf = new GeometryFactory();
        Point point = gf.createPoint(new Coordinate(1.2345678, 2.3456789));
        feature.setGeometry(point);
        feature.setLayer(layer);
        layer.addFeature(feature);

        layerRepository.save(layer);

        return "ok";
    }

}
