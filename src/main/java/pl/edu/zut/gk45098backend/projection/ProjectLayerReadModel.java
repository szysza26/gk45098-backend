package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.ProjectLayer;

public class ProjectLayerReadModel {
    private Long id;
    private String nameInLegend;
    private Integer zIndex;
    private StyleReadModel styleReadModel;
    private Long layerId;

    public ProjectLayerReadModel() { }

    public ProjectLayerReadModel(ProjectLayer projectLayer) {
        id = projectLayer.getId();
        nameInLegend = projectLayer.getNameInLegend();
        zIndex = projectLayer.getzIndex();
        styleReadModel = new StyleReadModel(projectLayer.getStyle());
        layerId = projectLayer.getLayer().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public StyleReadModel getStyleReadModel() {
        return styleReadModel;
    }

    public void setStyleReadModel(StyleReadModel styleReadModel) {
        this.styleReadModel = styleReadModel;
    }

    public Long getLayerId() {
        return layerId;
    }

    public void setLayerId(Long layerId) {
        this.layerId = layerId;
    }
}
