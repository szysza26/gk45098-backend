package pl.edu.zut.gk45098backend.service;

import org.junit.jupiter.api.Test;
import pl.edu.zut.gk45098backend.model.Layer;
import pl.edu.zut.gk45098backend.model.User;
import pl.edu.zut.gk45098backend.projection.*;
import pl.edu.zut.gk45098backend.repository.LayerRepository;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LayerServiceTest {

    @Test
    void getLayersShouldReturnLayerList() {
        // given
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        LayerService layerService = new LayerService(layerRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        when(layerRepository.findAllByUser(any(User.class))).thenReturn(prepareLayerList());

        // then
        List<LayerInListReadModel> layers = layerService.getLayers();

        // when
        assertThat(layers).hasSize(3);
    }

    private List<Layer> prepareLayerList() {
        Layer layer1 = new Layer();
        Layer layer2 = new Layer();
        Layer layer3 = new Layer();

        return Arrays.asList(layer1, layer2, layer3);
    }

    @Test
    void getLayerShouldReturnLayer() {
        // given
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        LayerService layerService = new LayerService(layerRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        Optional<Layer> layerOptional = Optional.of(new Layer());
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);

        // when
        LayerReadModel layerReadModel = layerService.getLayer(1L);

        // then
        assertThat(layerReadModel).isNotNull();
    }

    @Test
    void getLayerShouldThrowEntityNotFoundException() {
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        LayerService layerService = new LayerService(layerRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        Optional<Layer> layerOptional = Optional.empty();
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> layerService.getLayer(1L));
    }

    @Test
    void addLayerShouldSaveLayer() {
        // given
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        LayerService layerService = new LayerService(layerRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        LayerWriteModel layerWriteModel = mock(LayerWriteModel.class);
        Layer layer = new Layer();
        when(layerWriteModel.toLayer()).thenReturn(layer);

        // when
        layerService.addLayer(layerWriteModel);

        // then
        verify(layerRepository).save(any(Layer.class));
    }

    @Test
    void editLayerShouldUpdateExistLayer() {
        // given
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        LayerService layerService = new LayerService(layerRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        Layer layer = new Layer();
        Optional<Layer> layerOptional = Optional.of(new Layer());
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);
        LayerWriteModel layerWriteModel = mock(LayerWriteModel.class);

        // when
        layerService.editLayer(layerWriteModel, 1L);

        // then
        verify(layerWriteModel).updateLayer(any(Layer.class));
        verify(layerRepository).save(any(Layer.class));
    }

    @Test
    void editLayerShouldThrowEntityNotFoundException() {
        // given
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        LayerService layerService = new LayerService(layerRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        Optional<Layer> layerOptional = Optional.empty();
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);
        LayerWriteModel layerWriteModel = mock(LayerWriteModel.class);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> layerService.editLayer(layerWriteModel, 1L));
        verify(layerRepository, never()).save(any(Layer.class));
    }

    @Test
    void deleteLayerShouldDeleteLayer() {
        // given
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        LayerService layerService = new LayerService(layerRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        Optional<Layer> layerOptional = Optional.of(new Layer());
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);

        // when
        layerService.deleteLayer(1L);

        // then
        verify(layerRepository).delete(any(Layer.class));
    }

    @Test
    void deleteLayerShouldThrowEntityNotFoundException() {
        // given
        LayerRepository layerRepository = mock(LayerRepository.class);
        UserService userService = mock(UserService.class);
        LayerService layerService = new LayerService(layerRepository, userService);
        User user = new User();
        when(userService.getCurrentUser()).thenReturn(user);
        Optional<Layer> layerOptional = Optional.empty();
        when(layerRepository.findByIdAndUser(any(Long.class), any(User.class))).thenReturn(layerOptional);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> layerService.deleteLayer(1L));
        verify(layerRepository, never()).delete(any(Layer.class));
    }
}