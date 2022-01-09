package pl.edu.zut.gk45098backend.model;

import javax.persistence.*;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.locationtech.jts.geom.Geometry;
import java.util.HashMap;
import java.util.Map;

@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
public class Feature {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "Geometry")
    private Geometry geometry;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String, Object> properties;
    @ManyToOne
    @JoinColumn(name = "layer_id", nullable = false)
    private Layer layer;

    public Feature() {
        this.properties = new HashMap<String, Object>();
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

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }
}
