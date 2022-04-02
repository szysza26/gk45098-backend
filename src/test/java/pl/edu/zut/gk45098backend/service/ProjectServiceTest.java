package pl.edu.zut.gk45098backend.service;

import org.junit.jupiter.api.Test;
import pl.edu.zut.gk45098backend.model.Project;
import pl.edu.zut.gk45098backend.model.User;
import pl.edu.zut.gk45098backend.projection.ProjectInListReadModel;
import pl.edu.zut.gk45098backend.projection.ProjectReadModel;
import pl.edu.zut.gk45098backend.repository.ProjectRepository;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

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
        ProjectReadModel project = projectService.getProject(1L);

        // then
        assertThat(project).isNotNull();
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
}