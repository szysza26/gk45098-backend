package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.ProjectLayer;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class ProjectLayerWriteModel {

    @NotEmpty
    @Size(min = 1, max = 255)
    private String nameInLegend;

    @NotNull
    @Min(value = 0)
    private Integer zIndex;

    @Valid
    private StyleWriteModel style;

    @NotNull
    private Long projectId;

    @NotNull
    private Long layerId;

    public ProjectLayerWriteModel() { }

    public String getNameInLegend() {
        return nameInLegend;
    }

    public void setNameInLegend(String nameInLegend) {
        this.nameInLegend = nameInLegend;
    }

    public Integer getzIndex() {
        return zIndex;
    }

    public void setzIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }

    public StyleWriteModel getStyle() {
        return style;
    }

    public void setStyle(StyleWriteModel style) {
        this.style = style;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getLayerId() {
        return layerId;
    }

    public void setLayerId(Long layerId) {
        this.layerId = layerId;
    }

    public ProjectLayer toProjectLayer() {
        ProjectLayer projectLayer = new ProjectLayer();
        updateProjectLayer(projectLayer);
        return projectLayer;
    }

    public void updateProjectLayer(ProjectLayer projectLayer) {
        projectLayer.setNameInLegend(nameInLegend);
        projectLayer.setzIndex(zIndex);
        projectLayer.setStyle(style.toStyle());
    }
}
