package de.jenssproede.subapi.service.services;

import de.jenssproede.subapi.pojo.Series;
import de.jenssproede.subapi.service.AbstractService;

import java.util.ArrayList;
import java.util.List;

public class TV4User extends AbstractService {

    @Override
    public void register(String username, String password) {

    }

    @Override
    public List<Series> searchSeries(String series) {
        List<Series> seriesList = new ArrayList<>();
        seriesList.add(new Series("Wayward Pines", "link"));
        return seriesList;
    }

    @Override
    public List<String> searchSeasons(String season) {

        return null;
    }

    @Override
    public List<String> searchEpisode(String episode) {

        return null;
    }
}
