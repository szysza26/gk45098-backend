package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.Project;

import java.math.BigDecimal;

public class ProjectWriteModel {
    private String name;
    private String description;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private BigDecimal zoom;

    public ProjectWriteModel() { }

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

    public Project toProject() {
        Project project = new Project();
        updateProject(project);
        return project;
    }

    public void updateProject(Project project) {
        project.setName(name);
        project.setDescription(description);
        project.setLongitude(longitude);
        project.setLatitude(latitude);
        project.setZoom(zoom);
    }
}
