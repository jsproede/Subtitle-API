package de.jenssproede.subapi.pojo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
public class Episode {

    @JsonProperty
    private String episodeName;

    @JsonProperty
    private String language;

    @JsonProperty
    private List<Resolution> resolutions;

    public Episode() {
        resolutions = new ArrayList<>();
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Resolution> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<Resolution> resolutions) {
        this.resolutions = resolutions;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "episodeName='" + episodeName + '\'' +
                ", language='" + language + '\'' +
                ", resolutions=" + resolutions +
                '}';
    }
}
