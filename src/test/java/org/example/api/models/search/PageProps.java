package org.example.api.models.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageProps {
    private Metadata metadata;
    private CollectionData collectionData;

    public Metadata getMetadata() { return metadata; }
    public void setMetadata(Metadata metadata) { this.metadata = metadata; }
    public CollectionData getCollectionData() { return collectionData; }
    public void setCollectionData(CollectionData collectionData) { this.collectionData = collectionData; }
}