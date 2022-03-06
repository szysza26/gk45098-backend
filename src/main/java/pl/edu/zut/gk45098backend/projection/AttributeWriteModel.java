package pl.edu.zut.gk45098backend.projection;

import pl.edu.zut.gk45098backend.model.Attribute;

public class AttributeWriteModel {
    private String name;
    private String type;

    public AttributeWriteModel() { }

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

    public Attribute toAttribute() {
        Attribute attribute = new Attribute();
        updateAttribute(attribute);
        return attribute;
    }

    public void updateAttribute(Attribute attribute) {
        attribute.setName(name);
        attribute.setType(type);
    }
}
