package pl.edu.zut.gk45098backend.service;

import org.springframework.stereotype.Service;
import pl.edu.zut.gk45098backend.model.Layer;
import pl.edu.zut.gk45098backend.model.Project;
import pl.edu.zut.gk45098backend.model.ProjectLayer;
import pl.edu.zut.gk45098backend.projection.ProjectLayerReadModel;
import pl.edu.zut.gk45098backend.projection.ProjectLayerWriteModel;
import pl.edu.zut.gk45098backend.repository.LayerRepository;
import pl.edu.zut.gk45098backend.repository.ProjectLayerRepository;
import pl.edu.zut.gk45098backend.repository.ProjectRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectLayerService {

    private final ProjectLayerRepository projectLayerRepository;
    private final ProjectRepository projectRepository;
    private final LayerRepository layerRepository;

    public ProjectLayerService(ProjectLayerRepository projectLayerRepository, ProjectRepository projectRepository, LayerRepository layerRepository) {
        this.projectLayerRepository = projectLayerRepository;
        this.projectRepository = projectRepository;
        this.layerRepository = layerRepository;
    }

    public void addProjectLayer(ProjectLayerWriteModel projectLayerWriteModel){
        ProjectLayer projectLayer = projectLayerWriteModel.toProjectLayer();
        Project project = projectRepository.findById(projectLayerWriteModel.getProjectId()).orElseThrow(EntityNotFoundException::new);
        Layer layer = layerRepository.findById(projectLayerWriteModel.getLayerId()).orElseThrow(EntityNotFoundException::new);

        projectLayer.setProject(project);
        projectLayer.setLayer(layer);

        projectLayerRepository.save(projectLayer);
    }

    public ProjectLayerReadModel getProjectLayer(Long id) {
        ProjectLayer projectLayer = projectLayerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new ProjectLayerReadModel(projectLayer);
    }

    public List<ProjectLayerReadModel> getProjectLayers() {
        return projectLayerRepository.findAll().stream()
                .map(ProjectLayerReadModel::new)
                .collect(Collectors.toList());
    }

    public void editProjectLayer(ProjectLayerWriteModel projectLayerWriteModel, Long id) {
        ProjectLayer projectLayer = projectLayerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Project project = projectRepository.findById(projectLayerWriteModel.getProjectId()).orElseThrow(EntityNotFoundException::new);
        Layer layer = layerRepository.findById(projectLayerWriteModel.getLayerId()).orElseThrow(EntityNotFoundException::new);

        projectLayerWriteModel.updateProjectLayer(projectLayer);
        projectLayer.setProject(project);
        projectLayer.setLayer(layer);

        projectLayerRepository.save(projectLayer);
    }

    public void deleteProjectLayer(Long id) {
        ProjectLayer projectLayer = projectLayerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        projectLayerRepository.delete(projectLayer);
    }

}
