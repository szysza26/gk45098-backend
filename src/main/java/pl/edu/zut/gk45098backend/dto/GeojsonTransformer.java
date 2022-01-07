package pl.edu.zut.gk45098backend.dto;

import org.springframework.stereotype.Service;
import org.wololo.geojson.FeatureCollection;
import org.wololo.geojson.Geometry;
import org.wololo.jts2geojson.GeoJSONReader;
import org.wololo.jts2geojson.GeoJSONWriter;
import pl.edu.zut.gk45098backend.model.Feature;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GeojsonTransformer {

    public Set<Feature> transformFromGeojsonFeatureCollection(FeatureCollection featureCollection) {
        return Arrays.stream(featureCollection.getFeatures())
                .map(this::transformGeojsonFeatureToEntity)
                .collect(Collectors.toSet());
    }

    public Feature transformGeojsonFeatureToEntity(org.wololo.geojson.Feature geojsonFeature){
        Feature feature = new Feature();

        feature.setGeometry(new GeoJSONReader().read(geojsonFeature.getGeometry()));
        feature.setProperties(geojsonFeature.getProperties());

        return feature;
    }

    public FeatureCollection transformToGeojsonFeatureCollection(Set<Feature> features) {
        org.wololo.geojson.Feature[] geojsonFeatures = new org.wololo.geojson.Feature[features.size()];
        int i = 0;
        for(Feature feature : features){
            geojsonFeatures[i++] = this.transformEntityToGeojsonFeature(feature);
        }

        return new FeatureCollection(geojsonFeatures);
    }

    public org.wololo.geojson.Feature transformEntityToGeojsonFeature(Feature feature) {
        Geometry geometry = new GeoJSONWriter().write(feature.getGeometry());
        Map<String, Object> properties = feature.getProperties();
        return new org.wololo.geojson.Feature(feature.getId(), geometry, properties);
    }

}
