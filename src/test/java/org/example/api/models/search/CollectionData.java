package org.example.api.models.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionData {
    private int id;
    private String shortName;
    private String slug;
    private List<ContentSection> contentSections;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getShortName() { return shortName; }
    public void setShortName(String shortName) { this.shortName = shortName; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public List<ContentSection> getContentSections() { return contentSections; }
    public void setContentSections(List<ContentSection> contentSections) { this.contentSections = contentSections; }
}