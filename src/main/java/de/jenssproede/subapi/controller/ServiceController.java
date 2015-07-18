package de.jenssproede.subapi.controller;

import de.jenssproede.subapi.data.ServiceHolder;
import de.jenssproede.subapi.pojo.Episode;
import de.jenssproede.subapi.pojo.Season;
import de.jenssproede.subapi.pojo.Series;
import de.jenssproede.subapi.service.IService;
import de.jenssproede.subapi.service.Services;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ServiceController {

    public static final String SUCCESS = "{ \"success\": true }";
    public static final String ERROR = "{ \"error\": true }";

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam(value = "service") String wantedService, @RequestParam String token) {
        IService service = Services.getInstance().getService(wantedService);

        if (service != null) {
            ServiceHolder.registerTokenWithService(token, service);
            return service.getClass().getSimpleName();
        }

        return ERROR;
    }

    @RequestMapping(value = "/searchSeries", method = RequestMethod.POST)
    @ResponseBody
    public List<Series> searchSeries(@RequestParam String token, @RequestParam String series) {
        if (ServiceHolder.tokenExists(token)) {
            return ServiceHolder.getService(token).searchSeries(series);
        } else {
            return Collections.emptyList();
        }
    }

    @RequestMapping(value = "/searchSeasons", method = RequestMethod.POST)
    @ResponseBody
    public List<Season> searchSeasons(@RequestParam String token, @RequestParam String name, @RequestParam String link) {
        if (ServiceHolder.tokenExists(token)) {
            Series series = new Series(name, link);
            return ServiceHolder.getService(token).searchSeasons(series);
        } else {
            return Collections.emptyList();
        }
    }

    @RequestMapping(value = "/searchEpisodes", method = RequestMethod.POST)
    @ResponseBody
    public List<Episode> searchEpisodes(@RequestParam String token, @RequestParam String name, @RequestParam String link) {
        if (ServiceHolder.tokenExists(token)) {
            Season season = new Season(name, link);
            return ServiceHolder.getService(token).searchEpisodes(season);
        } else {
            return Collections.emptyList();
        }
    }

    @RequestMapping(value = "/services", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<String> getServices() {
        return  Services.getInstance().getAvailableServices().stream().map(
                    service -> service.getClass().getSimpleName()
                ).collect(Collectors.toList());
    }
}
