package pl.edu.zut.gk45098backend.model;

import javax.persistence.*;

@Entity
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pointColor;
    private String strokeColor;
    private String fillColor;
    private Float pointSize;
    private Float strokeWidth;
    private String strokeStyle;
    @OneToOne(mappedBy = "style")
    private ProjectLayer projectLayer;

    public Style() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPointColor() {
        return pointColor;
    }

    public void setPointColor(String pointColor) {
        this.pointColor = pointColor;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public Float getPointSize() {
        return pointSize;
    }

    public void setPointSize(Float pointSize) {
        this.pointSize = pointSize;
    }

    public Float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(Float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getStrokeStyle() {
        return strokeStyle;
    }

    public void setStrokeStyle(String strokeStyle) {
        this.strokeStyle = strokeStyle;
    }

    public ProjectLayer getProjectLayer() {
        return projectLayer;
    }

    public void setProjectLayer(ProjectLayer projectLayer) {
        this.projectLayer = projectLayer;
    }
}
