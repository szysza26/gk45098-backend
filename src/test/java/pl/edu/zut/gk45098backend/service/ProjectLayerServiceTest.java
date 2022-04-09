package pl.edu.zut.gk45098backend.service;

import org.junit.jupiter.api.Test;
import pl.edu.zut.gk45098backend.model.*;
import pl.edu.zut.gk45098backend.projection.ProjectLayerReadModel;
import pl.edu.zut.gk45098backend.projection.ProjectLayerWriteModel;
import pl.edu.zut.gk45098backend.projection.StyleWriteModel;
import pl.edu.zut.gk45098backend.repository.LayerRepository;
import pl.edu.zut.gk45098backend.repository.ProjectLayerRepository;
import pl.edu.zut.gk45098backend.repository.ProjectRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjectLayerServiceTest {

    @Test
    void getProjectLayerShouldReturnProjectLayerReadModel() {
        // given
        ProjectLayerRepository projectLayerRepository = mock(ProjectLayerRepository.class);
        UserService userService = mock(UserService.class);
        ProjectLayerService projectLayerService = new ProjectLayerService(projectLayerRepository, null, null, userService);

        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        ProjectLayer projectLayer = new ProjectLayer();
        projectLayer.setStyle(new Style());
        projectLayer.setProject(new Project());
        projectLayer.setLayer(new Layer());
        Optional<ProjectLayer> optionalProjectLayer = Optional.of(projectLayer);
        when(projectLayerRepository.findByIdAndProject_User(any(Long.class), any(User.class))).thenReturn(optionalProjectLayer);

        // when
        ProjectLayerReadModel projectLayerReadModel = projectLayerService.getProjectLayer(1L);

        // then
        assertThat(projectLayerReadModel).isNotNull();
    }

    @Test
    void getProjectLayerShouldThrowEntityNotFoundException() {
        // given
        ProjectLayerRepository projectLayerRepository = mock(ProjectLayerRepository.class);
        UserService userService = mock(UserService.class);
        ProjectLayerService projectLayerService = new ProjectLayerService(projectLayerRepository, null, null, userService);

        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        Optional<ProjectLayer> optionalProjectLayer = Optional.empty();
        when(projectLayerRepository.findByIdAndProject_User(any(Long.class), any(User.class))).thenReturn(optionalProjectLayer);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> projectLayerService.getProjectLayer(1L));
    }

    @Test
    void addProjectLayerShouldSaveProjectLayer() {
        //given
        ProjectLayerRepository projectLayerRepository = mock(ProjectLayerRepository.class);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        ProjectLayerService projectLayerService = new ProjectLayerService(projectLayerRepository, projectRepository, layerRepository, userService);

        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        Optional<Project> projectOptional = Optional.of(new Project());
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);
        Optional<Layer> layerOptional = Optional.of(new Layer());
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);

        ProjectLayerWriteModel projectLayerWriteModel = new ProjectLayerWriteModel();
        projectLayerWriteModel.setStyle(new StyleWriteModel());
        projectLayerWriteModel.setProjectId(1L);
        projectLayerWriteModel.setLayerId(1L);

        // when
        projectLayerService.addProjectLayer(projectLayerWriteModel);

        // then
        verify(projectLayerRepository).save(any(ProjectLayer.class));
    }

    @Test
    void addProjectLayerShouldThrowEntityNotFoundExceptionWhenProjectNotExist() {
        //given
        ProjectLayerRepository projectLayerRepository = mock(ProjectLayerRepository.class);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        ProjectLayerService projectLayerService = new ProjectLayerService(projectLayerRepository, projectRepository, layerRepository, userService);

        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        Optional<Project> projectOptional = Optional.empty();
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);
        Optional<Layer> layerOptional = Optional.of(new Layer());
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);

        ProjectLayerWriteModel projectLayerWriteModel = new ProjectLayerWriteModel();
        projectLayerWriteModel.setStyle(new StyleWriteModel());
        projectLayerWriteModel.setProjectId(1L);
        projectLayerWriteModel.setLayerId(1L);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> projectLayerService.addProjectLayer(projectLayerWriteModel));
        verify(projectLayerRepository, never()).save(any(ProjectLayer.class));
    }

    @Test
    void addProjectLayerShouldThrowEntityNotFoundExceptionWhenLayerNotExist() {
        //given
        ProjectLayerRepository projectLayerRepository = mock(ProjectLayerRepository.class);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        ProjectLayerService projectLayerService = new ProjectLayerService(projectLayerRepository, projectRepository, layerRepository, userService);

        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        Optional<Project> projectOptional = Optional.of(new Project());
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);
        Optional<Layer> layerOptional = Optional.empty();
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);

        ProjectLayerWriteModel projectLayerWriteModel = new ProjectLayerWriteModel();
        projectLayerWriteModel.setStyle(new StyleWriteModel());
        projectLayerWriteModel.setProjectId(1L);
        projectLayerWriteModel.setLayerId(1L);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> projectLayerService.addProjectLayer(projectLayerWriteModel));
        verify(projectLayerRepository, never()).save(any(ProjectLayer.class));
    }

    @Test
    void editProjectLayerShouldUpdateExistProjectLayer() {
        //given
        ProjectLayerRepository projectLayerRepository = mock(ProjectLayerRepository.class);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        ProjectLayerService projectLayerService = new ProjectLayerService(projectLayerRepository, projectRepository, layerRepository, userService);

        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        Optional<ProjectLayer> projectLayerOptional = Optional.of(new ProjectLayer());
        when(projectLayerRepository.findByIdAndProject_User(any(Long.class), any(User.class))).thenReturn(projectLayerOptional);
        Optional<Project> projectOptional = Optional.of(new Project());
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);
        Optional<Layer> layerOptional = Optional.of(new Layer());
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);

        ProjectLayerWriteModel projectLayerWriteModel = new ProjectLayerWriteModel();
        projectLayerWriteModel.setStyle(new StyleWriteModel());
        projectLayerWriteModel.setProjectId(1L);
        projectLayerWriteModel.setLayerId(1L);

        // when
        projectLayerService.editProjectLayer(projectLayerWriteModel, 1L);

        // then
        verify(projectLayerRepository).save(any(ProjectLayer.class));
    }

    @Test
    void editProjectLayerShouldThrowEntityNotFoundExceptionWhenProjectLayerNotExist() {
        //given
        ProjectLayerRepository projectLayerRepository = mock(ProjectLayerRepository.class);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        ProjectLayerService projectLayerService = new ProjectLayerService(projectLayerRepository, projectRepository, layerRepository, userService);

        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        Optional<ProjectLayer> projectLayerOptional = Optional.empty();
        when(projectLayerRepository.findByIdAndProject_User(any(Long.class), any(User.class))).thenReturn(projectLayerOptional);
        Optional<Project> projectOptional = Optional.of(new Project());
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);
        Optional<Layer> layerOptional = Optional.of(new Layer());
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);

        ProjectLayerWriteModel projectLayerWriteModel = new ProjectLayerWriteModel();
        projectLayerWriteModel.setStyle(new StyleWriteModel());
        projectLayerWriteModel.setProjectId(1L);
        projectLayerWriteModel.setLayerId(1L);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> projectLayerService.editProjectLayer(projectLayerWriteModel, 1L));
        verify(projectLayerRepository, never()).save(any(ProjectLayer.class));
    }

    @Test
    void editProjectLayerShouldThrowEntityNotFoundExceptionWhenProjectNotExist() {
        //given
        ProjectLayerRepository projectLayerRepository = mock(ProjectLayerRepository.class);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        ProjectLayerService projectLayerService = new ProjectLayerService(projectLayerRepository, projectRepository, layerRepository, userService);

        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        Optional<ProjectLayer> projectLayerOptional = Optional.of(new ProjectLayer());
        when(projectLayerRepository.findByIdAndProject_User(any(Long.class), any(User.class))).thenReturn(projectLayerOptional);
        Optional<Project> projectOptional = Optional.empty();
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);
        Optional<Layer> layerOptional = Optional.of(new Layer());
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);

        ProjectLayerWriteModel projectLayerWriteModel = new ProjectLayerWriteModel();
        projectLayerWriteModel.setStyle(new StyleWriteModel());
        projectLayerWriteModel.setProjectId(1L);
        projectLayerWriteModel.setLayerId(1L);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> projectLayerService.editProjectLayer(projectLayerWriteModel, 1L));
        verify(projectLayerRepository, never()).save(any(ProjectLayer.class));
    }

    @Test
    void editProjectLayerShouldThrowEntityNotFoundExceptionWhenLayerNotExist() {
        //given
        ProjectLayerRepository projectLayerRepository = mock(ProjectLayerRepository.class);
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        ProjectLayerService projectLayerService = new ProjectLayerService(projectLayerRepository, projectRepository, layerRepository, userService);

        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        Optional<ProjectLayer> projectLayerOptional = Optional.of(new ProjectLayer());
        when(projectLayerRepository.findByIdAndProject_User(any(Long.class), any(User.class))).thenReturn(projectLayerOptional);
        Optional<Project> projectOptional = Optional.of(new Project());
        when(projectRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(projectOptional);
        Optional<Layer> layerOptional = Optional.empty();
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);

        ProjectLayerWriteModel projectLayerWriteModel = new ProjectLayerWriteModel();
        projectLayerWriteModel.setStyle(new StyleWriteModel());
        projectLayerWriteModel.setProjectId(1L);
        projectLayerWriteModel.setLayerId(1L);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> projectLayerService.editProjectLayer(projectLayerWriteModel, 1L));
        verify(projectLayerRepository, never()).save(any(ProjectLayer.class));
    }

    @Test
    void deleteProjectLayerShouldDeleteProjectLayer() {
        // given
        ProjectLayerRepository projectLayerRepository = mock(ProjectLayerRepository.class);
        UserService userService = mock(UserService.class);
        ProjectLayerService projectLayerService = new ProjectLayerService(projectLayerRepository, null, null, userService);

        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        Optional<ProjectLayer> optionalProjectLayer = Optional.of(new ProjectLayer());
        when(projectLayerRepository.findByIdAndProject_User(any(Long.class), any(User.class))).thenReturn(optionalProjectLayer);

        // when
        projectLayerService.deleteProjectLayer(1L);

        // then
        verify(projectLayerRepository).delete(any(ProjectLayer.class));
    }

    @Test
    void deleteProjectLayerShouldThrowEntityNotFound() {
        // given
        ProjectLayerRepository projectLayerRepository = mock(ProjectLayerRepository.class);
        UserService userService = mock(UserService.class);
        ProjectLayerService projectLayerService = new ProjectLayerService(projectLayerRepository, null, null, userService);

        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);

        Optional<ProjectLayer> optionalProjectLayer = Optional.empty();
        when(projectLayerRepository.findByIdAndProject_User(any(Long.class), any(User.class))).thenReturn(optionalProjectLayer);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> projectLayerService.deleteProjectLayer(1L));
    }
}