package pl.edu.zut.gk45098backend.model;

import javax.persistence.*;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Feature {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    private Geometry geometry;

    @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL)
    private List<Property> properties;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "layer_id", nullable = false)
    private Layer layer;

    public Feature() {
        this.properties = new ArrayList<Property>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property property){
        this.properties.add(property);
    }

    public void  removeProperty(Feature feature){
        this.properties.remove(feature);
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }
}
