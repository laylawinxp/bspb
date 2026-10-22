package org.example.api.models.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse {
    @JsonProperty("pageProps")
    private PageProps pageProps;

    public PageProps getPageProps() { return pageProps; }
    public void setPageProps(PageProps pageProps) { this.pageProps = pageProps; }
}