package pl.edu.zut.gk45098backend.projection;

import org.wololo.geojson.FeatureCollection;
import org.wololo.jts2geojson.GeoJSONReader;
import pl.edu.zut.gk45098backend.model.Attribute;
import pl.edu.zut.gk45098backend.model.Feature;
import pl.edu.zut.gk45098backend.model.Layer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LayerWriteModel {

    private String name;
    private String type;
    private FeatureCollection data;
    private Set<AttributeWriteModel> attributes = new HashSet<>();

    public LayerWriteModel() { }

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

    public Layer toLayer() {
        Layer layer = new Layer();
        updateLayer(layer);
        return layer;
    }

    public void updateLayer(Layer layer) {
        layer.setName(name);
        layer.setType(type);
        Set<Feature> features = transformFromGeojsonFeatureCollection(data);
        for(Feature feature : features){
            feature.setLayer(layer);
            layer.addFeature(feature);
        }
        for(AttributeWriteModel attributeWriteModel : attributes){
            Attribute attribute = attributeWriteModel.toAttribute();
            attribute.setLayer(layer);
            layer.addAttribute(attribute);
        }
    }

    protected Set<Feature> transformFromGeojsonFeatureCollection(FeatureCollection featureCollection) {
        return Arrays.stream(featureCollection.getFeatures())
                .map(this::transformGeojsonFeatureToEntity)
                .collect(Collectors.toSet());
    }

    protected Feature transformGeojsonFeatureToEntity(org.wololo.geojson.Feature geojsonFeature){
        Feature feature = new Feature();

        feature.setGeometry(new GeoJSONReader().read(geojsonFeature.getGeometry()));
        feature.setProperties(geojsonFeature.getProperties());

        return feature;
    }

    public Set<AttributeWriteModel> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<AttributeWriteModel> attributes) {
        this.attributes = attributes;
    }
}
