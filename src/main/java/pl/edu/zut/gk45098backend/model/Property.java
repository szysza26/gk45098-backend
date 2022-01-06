package pl.edu.zut.gk45098backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String key;

    private String value;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;

    public Property() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
