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
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectLayer> projectlayers;
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public Set<ProjectLayer> getProjectlayers() {
        return projectlayers;
    }

    public void addProjectLayer(ProjectLayer projectLayer){
        this.projectlayers.add(projectLayer);
    }

    public void removeProjectLayer(ProjectLayer projectLayer){
        this.projectlayers.remove(projectLayer);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
