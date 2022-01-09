package pl.edu.zut.gk45098backend.model;

import javax.persistence.*;

@Entity
public class ProjectLayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer zIndex;
    private String nameInLegend;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "style_id", nullable = false)
    private Style style;
    @ManyToOne
    @JoinColumn(name = "layer_id", nullable = false)
    private Layer layer;

    public ProjectLayer() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getzIndex() {
        return zIndex;
    }

    public void setzIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }

    public String getNameInLegend() {
        return nameInLegend;
    }

    public void setNameInLegend(String nameInLegend) {
        this.nameInLegend = nameInLegend;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }
}
