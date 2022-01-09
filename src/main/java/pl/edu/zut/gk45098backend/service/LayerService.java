package pl.edu.zut.gk45098backend.service;

import org.springframework.stereotype.Service;
import pl.edu.zut.gk45098backend.dto.GeojsonTransformer;
import pl.edu.zut.gk45098backend.dto.LayerDTO;
import pl.edu.zut.gk45098backend.model.Feature;
import pl.edu.zut.gk45098backend.model.Layer;
import pl.edu.zut.gk45098backend.repository.LayerRepository;
import java.util.Set;

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
        layer.setType(layerDTO.getType());

        Set<Feature> features = geojsonTransformer.transformFromGeojsonFeatureCollection(layerDTO.getData());
        for(Feature feature : features){
            feature.setLayer(layer);
            layer.addFeature(feature);
        }

        layerRepository.save(layer);
    }

    public LayerDTO getLayerByName(String name) {
        Layer layer = this.layerRepository.findFirstByName(name);
        LayerDTO layerDTO = new LayerDTO();
        layerDTO.setName(layer.getName());
        layerDTO.setType(layer.getType());
        layerDTO.setData(this.geojsonTransformer.transformToGeojsonFeatureCollection(layer.getFeatures()));
        return  layerDTO;
    }

}
