package pl.edu.zut.gk45098backend.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @Column(precision = 8, scale = 6)
    private BigDecimal longitude;
    @Column(precision = 8, scale = 6)
    private BigDecimal latitude;
    @Column(precision = 3, scale = 1)
    private BigDecimal zoom;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<ProjectLayer> projectlayers;

    public Project() {
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
