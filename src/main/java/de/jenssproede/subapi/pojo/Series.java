package de.jenssproede.subapi.pojo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class Series implements Comparable<Series> {

    @JsonProperty
    private String name;

    @JsonProperty
    private String link;

    public Series(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "Series{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public int compareTo(Series series) {
        return name.compareTo(series.getName());
    }
}
