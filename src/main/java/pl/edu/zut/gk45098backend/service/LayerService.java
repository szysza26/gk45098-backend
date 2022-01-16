package pl.edu.zut.gk45098backend.service;

import org.springframework.stereotype.Service;
import pl.edu.zut.gk45098backend.model.Feature;
import pl.edu.zut.gk45098backend.model.Layer;
import pl.edu.zut.gk45098backend.projection.LayerInListReadModel;
import pl.edu.zut.gk45098backend.projection.LayerReadModel;
import pl.edu.zut.gk45098backend.projection.LayerWriteModel;
import pl.edu.zut.gk45098backend.repository.FeatureRepository;
import pl.edu.zut.gk45098backend.repository.LayerRepository;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LayerService {

    private final LayerRepository layerRepository;
    private final FeatureRepository featureRepository;

    public LayerService(LayerRepository layerRepository, FeatureRepository featureRepository) {
        this.layerRepository = layerRepository;
        this.featureRepository = featureRepository;
    }

    public void addLayer(LayerWriteModel layerWriteModel) {
        Layer layer = layerWriteModel.toLayer();

        layerRepository.save(layer);
    }

    public LayerReadModel getLayer(Long id) {
        Layer layer = layerRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        LayerReadModel layerReadModel = new LayerReadModel(layer);

        return layerReadModel;
    }

    public List<LayerInListReadModel> getLayers() {
        return layerRepository.findAll().stream()
                .map(LayerInListReadModel::new)
                .collect(Collectors.toList());
    }

    public void editLayer(LayerWriteModel layerWriteModel, Long id) {
        Layer layer = layerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        for(Feature feature : layer.getFeatures()) {
            layer.removeFeature(feature);
            featureRepository.delete(feature);
        }
        layerWriteModel.updateLayer(layer);
        layerRepository.save(layer);
    }

    public void deleteLayer(Long id) {
        Layer layer = layerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        layerRepository.delete(layer);
    }
}
