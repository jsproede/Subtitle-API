package de.jenssproede.subapi.service.services;

import de.jenssproede.subapi.pojo.Series;
import de.jenssproede.subapi.service.AbstractService;

import java.util.List;

public class Subcentral extends AbstractService {

    @Override
    public void register(String username, String password) {

    }

    @Override
    public List<Series> searchSeries(String series) {

        return null;
    }

    @Override
    public List<String> searchSeasons(Series series) {

        return null;
    }

    @Override
    public List<String> searchEpisode(String episode) {

        return null;
    }
}
