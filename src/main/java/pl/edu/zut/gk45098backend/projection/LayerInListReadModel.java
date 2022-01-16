package pl.edu.zut.gk45098backend.projection;

import org.springframework.stereotype.Component;
import pl.edu.zut.gk45098backend.model.Layer;

@Component
public class LayerInListReadModel {

    private Long id;
    private String name;
    private String type;

    public LayerInListReadModel() { }

    public LayerInListReadModel(Layer layer) {
        id = layer.getId();
        name = layer.getName();
        type = layer.getType();
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
}