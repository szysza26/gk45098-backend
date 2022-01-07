package pl.edu.zut.gk45098backend.dto;

import org.springframework.stereotype.Component;
import org.wololo.geojson.FeatureCollection;

@Component
public class LayerDTO {

    private String name;

    private FeatureCollection data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FeatureCollection getData() {
        return data;
    }

    public void setData(FeatureCollection data) {
        this.data = data;
    }
}
