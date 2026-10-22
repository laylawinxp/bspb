package org.example.api.models.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentSection {
    private int id;
    @JsonProperty("__component")
    private String component;
    private Boolean isHidden;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getComponent() { return component; }
    public void setComponent(String component) { this.component = component; }
    public Boolean getIsHidden() { return isHidden; }
    public void setIsHidden(Boolean isHidden) { this.isHidden = isHidden; }
}