package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.Project;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectReadModel {
    private Long id;
    private String name;
    private String description;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private BigDecimal zoom;
    private Set<ProjectLayerReadModel> layers;

    public ProjectReadModel() { }

    public ProjectReadModel(Project project) {
        id = project.getId();
        name = project.getName();
        description = project.getDescription();
        longitude = project.getLongitude();
        latitude = project.getLatitude();
        zoom = project.getZoom();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getZoom() {
        return zoom;
    }

    public void setZoom(BigDecimal zoom) {
        this.zoom = zoom;
    }

    public Set<ProjectLayerReadModel> getLayers() {
        return layers;
    }

    public void setLayers(Set<ProjectLayerReadModel> layers) {
        this.layers = layers;
    }
}
