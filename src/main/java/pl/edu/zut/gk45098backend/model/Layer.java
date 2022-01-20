package pl.edu.zut.gk45098backend.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Layer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @OneToMany(mappedBy = "layer", cascade = CascadeType.ALL)
    private Set<ProjectLayer> projectlayers;

    @OneToMany(mappedBy = "layer", cascade = CascadeType.ALL)
    private Set<Feature> features;

    public Layer() {
        this.features = new HashSet<Feature>();
        this.projectlayers = new HashSet<ProjectLayer>();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void addFeature(Feature feature){
        this.features.add(feature);
    }

    public void removeFeature(Feature feature){
        this.features.remove(feature);
    }

    public Set<ProjectLayer> getProjectlayers() {
        return projectlayers;
    }

    public void addProjectLayer(ProjectLayer projectLayer){
        this.projectlayers.add(projectLayer);
    }

    public void removeProjectLayer(ProjectLayer projectLayer){
        this.projectlayers.remove(projectLayer);
    }
}
