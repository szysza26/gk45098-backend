package pl.edu.zut.gk45098backend.dto;

import org.springframework.stereotype.Component;
import org.wololo.geojson.Feature;

import java.util.Set;

@Component
public class LayerDTO {

    private String name;

    private Set<Feature> features;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }
}
