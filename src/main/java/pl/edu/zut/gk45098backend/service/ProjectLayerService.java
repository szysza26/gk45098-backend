package pl.edu.zut.gk45098backend.service;

import org.springframework.stereotype.Service;
import pl.edu.zut.gk45098backend.model.Layer;
import pl.edu.zut.gk45098backend.model.Project;
import pl.edu.zut.gk45098backend.model.ProjectLayer;
import pl.edu.zut.gk45098backend.model.User;
import pl.edu.zut.gk45098backend.projection.ProjectLayerReadModel;
import pl.edu.zut.gk45098backend.projection.ProjectLayerWriteModel;
import pl.edu.zut.gk45098backend.repository.LayerRepository;
import pl.edu.zut.gk45098backend.repository.ProjectLayerRepository;
import pl.edu.zut.gk45098backend.repository.ProjectRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class ProjectLayerService {

    private final ProjectLayerRepository projectLayerRepository;
    private final ProjectRepository projectRepository;
    private final LayerRepository layerRepository;
    private final UserService userService;

    public ProjectLayerService(ProjectLayerRepository projectLayerRepository, ProjectRepository projectRepository, LayerRepository layerRepository, UserService userService) {
        this.projectLayerRepository = projectLayerRepository;
        this.projectRepository = projectRepository;
        this.layerRepository = layerRepository;
        this.userService = userService;
    }

    public void addProjectLayer(ProjectLayerWriteModel projectLayerWriteModel){
        User user = userService.getCurrentUser();
        ProjectLayer projectLayer = projectLayerWriteModel.toProjectLayer();
        Project project = projectRepository.findByIdAndUser(projectLayerWriteModel.getProjectId(), user).orElseThrow(EntityNotFoundException::new);
        Layer layer = layerRepository.findByIdAndUser(projectLayerWriteModel.getLayerId(), user).orElseThrow(EntityNotFoundException::new);

        projectLayer.setProject(project);
        projectLayer.setLayer(layer);

        projectLayerRepository.save(projectLayer);
    }

    public ProjectLayerReadModel getProjectLayer(Long id) {
        User user = userService.getCurrentUser();
        ProjectLayer projectLayer = projectLayerRepository.findByIdAndProject_User(id, user).orElseThrow(EntityNotFoundException::new);
        return new ProjectLayerReadModel(projectLayer);
    }

    public void editProjectLayer(ProjectLayerWriteModel projectLayerWriteModel, Long id) {
        User user = userService.getCurrentUser();
        ProjectLayer projectLayer = projectLayerRepository.findByIdAndProject_User(id, user).orElseThrow(EntityNotFoundException::new);
        Project project = projectRepository.findByIdAndUser(projectLayerWriteModel.getProjectId(), user).orElseThrow(EntityNotFoundException::new);
        Layer layer = layerRepository.findByIdAndUser(projectLayerWriteModel.getLayerId(), user).orElseThrow(EntityNotFoundException::new);

        projectLayerWriteModel.updateProjectLayer(projectLayer);
        projectLayer.setProject(project);
        projectLayer.setLayer(layer);

        projectLayerRepository.save(projectLayer);
    }

    public void deleteProjectLayer(Long id) {
        User user = userService.getCurrentUser();
        ProjectLayer projectLayer = projectLayerRepository.findByIdAndProject_User(id, user).orElseThrow(EntityNotFoundException::new);
        projectLayerRepository.delete(projectLayer);
    }

}
