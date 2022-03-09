package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.Style;

import javax.validation.constraints.*;

public class StyleWriteModel {

    @NotEmpty
    @Size(min = 3, max = 30)
    private String pointColor;

    @NotEmpty
    @Size(min = 3, max = 30)
    private String strokeColor;

    @NotEmpty
    @Size(min = 3, max = 30)
    private String fillColor;

    @NotNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "100")
    private Float pointSize;

    @NotNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "100")
    private Float strokeWidth;

    public StyleWriteModel() { }

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

    public Style toStyle() {
        Style style = new Style();
        updateStyle(style);
        return style;
    }

    public void updateStyle(Style style) {
        style.setPointColor(pointColor);
        style.setStrokeColor(strokeColor);
        style.setFillColor(fillColor);
        style.setPointSize(pointSize);
        style.setStrokeWidth(strokeWidth);
    }
}
