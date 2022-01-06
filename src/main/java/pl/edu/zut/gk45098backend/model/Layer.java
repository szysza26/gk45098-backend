package pl.edu.zut.gk45098backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Layer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "layer", cascade = CascadeType.ALL)
    private List<Feature> features;

    public Layer() {
        this.features = new ArrayList<Feature>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void addFeature(Feature feature){
        this.features.add(feature);
    }

    public void removeFeature(Feature feature){
        this.features.remove(feature);
    }
}
