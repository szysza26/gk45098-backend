package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.Project;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectReadModel {
    private Long id;
    private String name;
    private Set<ProjectLayerReadModel> layers;

    public ProjectReadModel() { }

    public ProjectReadModel(Project project) {
        id = project.getId();
        name = project.getName();
        layers = project.getProjectlayers().stream()
                .map(ProjectLayerReadModel::new)
                .collect(Collectors.toSet());
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

    public Set<ProjectLayerReadModel> getLayers() {
        return layers;
    }

    public void setLayers(Set<ProjectLayerReadModel> layers) {
        this.layers = layers;
    }
}
