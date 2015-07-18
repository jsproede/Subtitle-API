package de.jenssproede.subapi.service.services;

import de.jenssproede.subapi.pojo.Series;
import de.jenssproede.subapi.service.AbstractService;
import de.jenssproede.subapi.service.HTMLParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TV4User extends AbstractService {

    public static final String MAIN_URI = "http://board.tv4user.de/index.php?page=Index";
    public static final String BOARD_URI = "http://board.tv4user.de/index.php?page=Board&boardID=%s";

    @Override
    public void register(String username, String password) {

    }

    @Override
    public List<Series> searchSeries(String series) {
        List<Series> seriesList = new ArrayList<>();
        Document document = HTMLParser.getInstance().getDocument(MAIN_URI);
        if (document != null) {
            Elements elements = document.select("select[name=boardID] option:contains(" + series + ")");

            Series s;
            for (Element element : elements) {
                s = new Series(element.text(), element.attr("value"));
                seriesList.add(s);
            }
        }

        return seriesList;
    }

    @Override
    public List<String> searchSeasons(Series series) {
        Document document = HTMLParser.getInstance().getDocument(String.format(BOARD_URI, series.getLink()));
        return null;
    }

    @Override
    public List<String> searchEpisode(String episode) {

        return null;
    }
}
