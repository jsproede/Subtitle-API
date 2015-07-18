package de.jenssproede.subapi.pojo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class Resolution {

    @JsonProperty
    private String resolution;

    @JsonProperty
    private String name;

    @JsonProperty
    private String link;

    public Resolution(String resolution, String name, String link) {
        this.resolution = resolution;
        this.name = name;
        this.link = link;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Resolution{" +
                "resolution='" + resolution + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
