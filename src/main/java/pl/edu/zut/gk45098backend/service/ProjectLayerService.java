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
import pl.edu.zut.gk45098backend.security.AuthenticationFacade;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectLayerService {

    private final ProjectLayerRepository projectLayerRepository;
    private final ProjectRepository projectRepository;
    private final LayerRepository layerRepository;
    private final AuthenticationFacade authenticationFacade;

    public ProjectLayerService(ProjectLayerRepository projectLayerRepository, ProjectRepository projectRepository, LayerRepository layerRepository, AuthenticationFacade authenticationFacade) {
        this.projectLayerRepository = projectLayerRepository;
        this.projectRepository = projectRepository;
        this.layerRepository = layerRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public void addProjectLayer(ProjectLayerWriteModel projectLayerWriteModel){
        User user = authenticationFacade.getUser();
        ProjectLayer projectLayer = projectLayerWriteModel.toProjectLayer();
        Project project = projectRepository.findByIdAndUser(projectLayerWriteModel.getProjectId(), user).orElseThrow(EntityNotFoundException::new);
        Layer layer = layerRepository.findByIdAndUser(projectLayerWriteModel.getLayerId(), user).orElseThrow(EntityNotFoundException::new);

        projectLayer.setProject(project);
        projectLayer.setLayer(layer);

        projectLayerRepository.save(projectLayer);
    }

    public ProjectLayerReadModel getProjectLayer(Long id) {
        User user = authenticationFacade.getUser();
        ProjectLayer projectLayer = projectLayerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(!projectLayer.getProject().getUser().getId().equals(user.getId()) || !projectLayer.getLayer().getUser().getId().equals(user.getId())) throw new EntityNotFoundException();
        return new ProjectLayerReadModel(projectLayer);
    }

    public void editProjectLayer(ProjectLayerWriteModel projectLayerWriteModel, Long id) {
        User user = authenticationFacade.getUser();
        ProjectLayer projectLayer = projectLayerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(!projectLayer.getProject().getUser().getId().equals(user.getId()) || !projectLayer.getLayer().getUser().getId().equals(user.getId())) throw new EntityNotFoundException();
        Project project = projectRepository.findByIdAndUser(projectLayerWriteModel.getProjectId(), user).orElseThrow(EntityNotFoundException::new);
        Layer layer = layerRepository.findByIdAndUser(projectLayerWriteModel.getLayerId(), user).orElseThrow(EntityNotFoundException::new);

        projectLayerWriteModel.updateProjectLayer(projectLayer);
        projectLayer.setProject(project);
        projectLayer.setLayer(layer);

        projectLayerRepository.save(projectLayer);
    }

    public void deleteProjectLayer(Long id) {
        User user = authenticationFacade.getUser();
        ProjectLayer projectLayer = projectLayerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(!projectLayer.getProject().getUser().getId().equals(user.getId()) || !projectLayer.getLayer().getUser().getId().equals(user.getId())) throw new EntityNotFoundException();
        projectLayerRepository.delete(projectLayer);
    }

}
