package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.Project;

import java.math.BigDecimal;

public class ProjectInListReadModel {
    private Long id;
    private String name;

    public ProjectInListReadModel() { }

    public ProjectInListReadModel(Project project) {
        id = project.getId();
        name = project.getName();
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
}
