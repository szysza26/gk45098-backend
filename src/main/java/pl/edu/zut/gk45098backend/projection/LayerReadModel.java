package pl.edu.zut.gk45098backend.projection;

import org.wololo.geojson.FeatureCollection;
import org.wololo.geojson.Geometry;
import org.wololo.jts2geojson.GeoJSONWriter;
import pl.edu.zut.gk45098backend.model.Feature;
import pl.edu.zut.gk45098backend.model.Layer;
import java.util.Map;
import java.util.Set;

public class LayerReadModel {

    private Long id;
    private String name;
    private String type;
    private FeatureCollection data;

    public LayerReadModel() { }

    public LayerReadModel(Layer layer) {
        id = layer.getId();
        name = layer.getName();
        type = layer.getType();
        data = transformToGeojsonFeatureCollection(layer.getFeatures());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FeatureCollection getData() {
        return data;
    }

    public void setData(FeatureCollection data) {
        this.data = data;
    }

    protected FeatureCollection transformToGeojsonFeatureCollection(Set<Feature> features) {
        org.wololo.geojson.Feature[] geojsonFeatures = new org.wololo.geojson.Feature[features.size()];
        int i = 0;
        for(Feature feature : features){
            geojsonFeatures[i++] = this.transformEntityToGeojsonFeature(feature);
        }

        return new FeatureCollection(geojsonFeatures);
    }

    protected org.wololo.geojson.Feature transformEntityToGeojsonFeature(Feature feature) {
        Geometry geometry = new GeoJSONWriter().write(feature.getGeometry());
        Map<String, Object> properties = feature.getProperties();
        return new org.wololo.geojson.Feature(feature.getId(), geometry, properties);
    }
}