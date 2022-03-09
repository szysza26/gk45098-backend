package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.Project;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProjectWriteModel {

    @NotEmpty
    @Size(min = 1, max = 255)
    private String name;

    public ProjectWriteModel() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project toProject() {
        Project project = new Project();
        updateProject(project);
        return project;
    }

    public void updateProject(Project project) {
        project.setName(name);
    }
}
