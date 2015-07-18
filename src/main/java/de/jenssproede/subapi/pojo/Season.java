package de.jenssproede.subapi.pojo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class Season implements Comparable<Season> {

    @JsonProperty
    private String seasonName;

    @JsonProperty
    private String link;

    public Season(String seasonName, String link) {
        this.seasonName = seasonName;
        this.link = link;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public int compareTo(Season season) {
        return seasonName.compareTo(season.getSeasonName());
    }
}
