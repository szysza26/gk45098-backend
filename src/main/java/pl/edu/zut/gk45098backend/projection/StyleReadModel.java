package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.Style;

public class StyleReadModel {
    private Long id;
    private String pointColor;
    private String strokeColor;
    private String fillColor;
    private Float pointSize;
    private Float strokeWidth;
    private String strokeStyle;

    public StyleReadModel() { }

    public StyleReadModel(Style style) {
        id = style.getId();
        pointColor = style.getPointColor();
        strokeColor = style.getStrokeColor();
        fillColor = style.getFillColor();
        pointSize = style.getPointSize();
        strokeWidth = style.getStrokeWidth();
        strokeStyle = style.getStrokeStyle();
    }

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
}
