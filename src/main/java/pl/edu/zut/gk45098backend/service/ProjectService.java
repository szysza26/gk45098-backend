package pl.edu.zut.gk45098backend.service;

import org.springframework.stereotype.Service;
import pl.edu.zut.gk45098backend.model.Project;
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

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void addProject(ProjectWriteModel projectWriteModel) {
        Project project = projectWriteModel.toProject();
        projectRepository.save(project);
    }

    public ProjectReadModel getProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new ProjectReadModel(project);
    }

    public List<ProjectInListReadModel> getProjects() {
        return projectRepository.findAll().stream()
                .map(ProjectInListReadModel::new)
                .collect(Collectors.toList());
    }

    public void editProject(ProjectWriteModel projectWriteModel, Long id) {
        Project project = projectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        projectWriteModel.updateProject(project);
        projectRepository.save(project);
    }

    public  void deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        projectRepository.delete(project);
    }

}
