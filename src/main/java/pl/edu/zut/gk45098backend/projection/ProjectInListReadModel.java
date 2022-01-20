package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.Project;

import java.math.BigDecimal;

public class ProjectInListReadModel {
    private Long id;
    private String name;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private BigDecimal zoom;

    public ProjectInListReadModel() { }

    public ProjectInListReadModel(Project project) {
        id = project.getId();
        name = project.getName();
        longitude = project.getLongitude();
        latitude = project.getLatitude();
        zoom = project.getZoom();
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
}
