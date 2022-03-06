package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.Attribute;

public class AttributeReadModel {
    private Long id;
    private String name;
    private String type;

    public AttributeReadModel() { }

    public AttributeReadModel(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public AttributeReadModel(Attribute attribute) {
        this.id = attribute.getId();
        this.name = attribute.getName();
        this.type = attribute.getType();
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
