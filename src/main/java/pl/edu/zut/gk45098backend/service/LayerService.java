package pl.edu.zut.gk45098backend.service;

import org.springframework.stereotype.Service;
import pl.edu.zut.gk45098backend.model.Feature;
import pl.edu.zut.gk45098backend.model.Layer;
import pl.edu.zut.gk45098backend.model.User;
import pl.edu.zut.gk45098backend.projection.LayerInListReadModel;
import pl.edu.zut.gk45098backend.projection.LayerReadModel;
import pl.edu.zut.gk45098backend.projection.LayerWriteModel;
import pl.edu.zut.gk45098backend.repository.FeatureRepository;
import pl.edu.zut.gk45098backend.repository.LayerRepository;
import pl.edu.zut.gk45098backend.security.AuthenticationFacade;

import javax.persistence.EntityNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LayerService {

    private final LayerRepository layerRepository;
    private final AuthenticationFacade authenticationFacade;

    public LayerService(LayerRepository layerRepository, AuthenticationFacade authenticationFacade) {
        this.layerRepository = layerRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public void addLayer(LayerWriteModel layerWriteModel) {
        User user = authenticationFacade.getUser();
        Layer layer = layerWriteModel.toLayer();
        layer.setUser(user);
        layerRepository.save(layer);
    }

    public LayerReadModel getLayer(Long id) {
        User user = authenticationFacade.getUser();
        Layer layer = layerRepository.findByIdAndUser(id, user).orElseThrow(EntityNotFoundException::new);
        LayerReadModel layerReadModel = new LayerReadModel(layer);
        return layerReadModel;
    }

    public List<LayerInListReadModel> getLayers() {
        User user = authenticationFacade.getUser();
        return layerRepository.findAllByUser(user).stream()
                .map(LayerInListReadModel::new)
                .collect(Collectors.toList());
    }

    public void editLayer(LayerWriteModel layerWriteModel, Long id) {
        User user = authenticationFacade.getUser();
        Layer layer = layerRepository.findByIdAndUser(id, user).orElseThrow(EntityNotFoundException::new);
        layer.clearFeatures();
        layer.clearAttributes();
        layerWriteModel.updateLayer(layer);
        layerRepository.save(layer);
    }

    public void deleteLayer(Long id) {
        User user = authenticationFacade.getUser();
        Layer layer = layerRepository.findByIdAndUser(id, user).orElseThrow(EntityNotFoundException::new);
        layerRepository.delete(layer);
    }
}
