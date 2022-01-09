package pl.edu.zut.gk45098backend.dto;

import org.springframework.stereotype.Component;
import org.wololo.geojson.FeatureCollection;

@Component
public class LayerDTO {

    private String name;

    private String type;

    private FeatureCollection data;

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
}
