package pl.edu.zut.gk45098backend.service;

import org.springframework.stereotype.Service;
import pl.edu.zut.gk45098backend.model.Project;
import pl.edu.zut.gk45098backend.model.User;
import pl.edu.zut.gk45098backend.projection.ProjectInListReadModel;
import pl.edu.zut.gk45098backend.projection.ProjectReadModel;
import pl.edu.zut.gk45098backend.projection.ProjectWriteModel;
import pl.edu.zut.gk45098backend.repository.ProjectRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    public ProjectService(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    public void addProject(ProjectWriteModel projectWriteModel) {
        User user = userService.getCurrentUser();
        Project project = projectWriteModel.toProject();
        project.setUser(user);
        projectRepository.save(project);
    }

    public ProjectReadModel getProject(Long id) {
        User user = userService.getCurrentUser();
        Project project = projectRepository.findByIdAndUser(id, user).orElseThrow(EntityNotFoundException::new);
        return new ProjectReadModel(project);
    }

    public List<ProjectInListReadModel> getProjects() {
        User user = userService.getCurrentUser();
        return projectRepository.findAllByUser(user).stream()
                .map(ProjectInListReadModel::new)
                .collect(Collectors.toList());
    }

    public void editProject(ProjectWriteModel projectWriteModel, Long id) {
        User user = userService.getCurrentUser();
        Project project = projectRepository.findByIdAndUser(id, user).orElseThrow(EntityNotFoundException::new);
        projectWriteModel.updateProject(project);
        projectRepository.save(project);
    }

    public  void deleteProject(Long id) {
        User user = userService.getCurrentUser();
        Project project = projectRepository.findByIdAndUser(id, user).orElseThrow(EntityNotFoundException::new);
        projectRepository.delete(project);
    }

}
