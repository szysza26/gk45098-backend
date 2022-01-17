package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.ProjectLayer;

public class ProjectLayerWriteModel {
    private String nameInLegend;
    private Integer zIndex;

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

    public ProjectLayer toProjectLayer() {
        ProjectLayer projectLayer = new ProjectLayer();
        updateProjectLayer(projectLayer);
        return projectLayer;
    }

    public void updateProjectLayer(ProjectLayer projectLayer) {
        projectLayer.setNameInLegend(nameInLegend);
        projectLayer.setzIndex(zIndex);
    }
}
