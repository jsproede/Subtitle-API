package de.jenssproede.subapi.service;

import de.jenssproede.subapi.pojo.Series;

import java.util.List;

public interface IService {
    void register(String token);
    void register(String username, String password);
    List<Series> searchSeries(String series);
    List<String> searchSeasons(Series series);
    List<String> searchEpisode(String episode);
}
