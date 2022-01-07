package pl.edu.zut.gk45098backend.service;

import org.springframework.stereotype.Service;
import pl.edu.zut.gk45098backend.dto.GeojsonTransformer;
import pl.edu.zut.gk45098backend.dto.LayerDTO;
import pl.edu.zut.gk45098backend.model.Feature;
import pl.edu.zut.gk45098backend.model.Layer;
import pl.edu.zut.gk45098backend.repository.LayerRepository;


@Service
public class LayerService {

    private LayerRepository layerRepository;
    private GeojsonTransformer geojsonTransformer;

    public LayerService(LayerRepository layerRepository, GeojsonTransformer geojsonTransformer) {
        this.layerRepository = layerRepository;
        this.geojsonTransformer = geojsonTransformer;
    }

    public void addLayer(LayerDTO layerDTO) {
        Layer layer = new Layer();
        layer.setName(layerDTO.getName());

        for(org.wololo.geojson.Feature geojsonFeature : layerDTO.getFeatures()){
            Feature feature = this.geojsonTransformer.transformGeojsonFeatureToEntity(geojsonFeature);
            feature.setLayer(layer);
            layer.addFeature(feature);
        }

        layerRepository.save(layer);
    }

}
