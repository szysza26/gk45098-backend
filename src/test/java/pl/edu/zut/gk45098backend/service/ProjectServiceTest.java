package pl.edu.zut.gk45098backend.service;

import org.junit.jupiter.api.Test;
import pl.edu.zut.gk45098backend.model.Project;
import pl.edu.zut.gk45098backend.model.User;
import pl.edu.zut.gk45098backend.projection.ProjectInListReadModel;
import pl.edu.zut.gk45098backend.projection.ProjectReadModel;
import pl.edu.zut.gk45098backend.projection.ProjectWriteModel;
import pl.edu.zut.gk45098backend.repository.ProjectRepository;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Test
    void getProjectsShouldReturnProjectList() {
        // given
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        UserService userService = mock(UserService.class);
        ProjectService projectService = new ProjectService(projectRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        when(projectRepository.findAllByUser(any(User.class))).thenReturn(prepareProjectList());

        // then
        List<ProjectInListReadModel> projects = projectService.getProjects();

        // when
        assertThat(projects).hasSize(3);
    }

    private List<Project> prepareProjectList() {
        Project project1 = new Project();
        Project project2 = new Project();
        Project project3 = new Project();

        return Arrays.asList(project1, project2, project3);
    }

    @Test
    void getProjectShouldReturnProject() {
        // given
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        UserService userService = mock(UserService.class);
        ProjectService projectService = new ProjectService(projectRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        Optional<Project> projectOptional = Optional.of(new Project());
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);

        // when
        ProjectReadModel projectReadModel = projectService.getProject(1L);

        // then
        assertThat(projectReadModel).isNotNull();
    }

    @Test
    void getProjectShouldThrowEntityNotFoundException() {
        // given
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        UserService userService = mock(UserService.class);
        ProjectService projectService = new ProjectService(projectRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        Optional<Project> projectOptional = Optional.empty();
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> projectService.getProject(1L));
    }

    @Test
    void addProjectShouldSaveProject() {
        // given
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        UserService userService = mock(UserService.class);
        ProjectService projectService = new ProjectService(projectRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        ProjectWriteModel projectWriteModel = new ProjectWriteModel();

        // when
        projectService.addProject(projectWriteModel);

        // then
        verify(projectRepository).save(any(Project.class));
    }

    @Test
    void editProjectShouldUpdateExistProject() {
        // given
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        UserService userService = mock(UserService.class);
        ProjectService projectService = new ProjectService(projectRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        Optional<Project> projectOptional = Optional.of(new Project());
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);
        ProjectWriteModel projectWriteModel = mock(ProjectWriteModel.class);

        // when
        projectService.editProject(projectWriteModel, 1L);

        // then
        verify(projectWriteModel).updateProject(any(Project.class));
        verify(projectRepository).save(any(Project.class));
    }

    @Test
    void editProjectShouldThrowEntityNotFoundException() {
        // given
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        UserService userService = mock(UserService.class);
        ProjectService projectService = new ProjectService(projectRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        Optional<Project> projectOptional = Optional.empty();
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);
        ProjectWriteModel projectWriteModel = new ProjectWriteModel();

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> projectService.editProject(projectWriteModel, 1L));
        verify(projectRepository, never()).save(any(Project.class));
    }

    @Test
    void deleteProjectShouldDeleteProject() {
        // given
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        UserService userService = mock(UserService.class);
        ProjectService projectService = new ProjectService(projectRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        Optional<Project> projectOptional = Optional.of(new Project());
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);

        // when
        projectService.deleteProject(1L);

        // then
        verify(projectRepository).delete(any(Project.class));
    }

    @Test
    void deleteProjectShouldThrowEntityNotFoundException() {
        // given
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        UserService userService = mock(UserService.class);
        ProjectService projectService = new ProjectService(projectRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        Optional<Project> projectOptional = Optional.empty();
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> projectService.deleteProject(1L));
        verify(projectRepository, never()).delete(any(Project.class));
    }
}