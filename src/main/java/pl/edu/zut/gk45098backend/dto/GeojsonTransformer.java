package pl.edu.zut.gk45098backend.dto;

import org.springframework.stereotype.Service;
import org.wololo.jts2geojson.GeoJSONReader;
import pl.edu.zut.gk45098backend.model.Feature;

@Service
public class GeojsonTransformer {

    public Feature transformGeojsonFeatureToEntity(org.wololo.geojson.Feature geojsonFeature){
        Feature feature = new Feature();

        feature.setGeometry(new GeoJSONReader().read(geojsonFeature.getGeometry()));
        feature.setProperties(geojsonFeature.getProperties());

        return feature;
    }

}
