package de.jenssproede.subapi.service.services;

import de.jenssproede.subapi.pojo.Episode;
import de.jenssproede.subapi.pojo.Resolution;
import de.jenssproede.subapi.pojo.Season;
import de.jenssproede.subapi.pojo.Series;
import de.jenssproede.subapi.service.AbstractService;
import de.jenssproede.subapi.service.HTMLParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class TV4User extends AbstractService {

    private static final String MAIN_URI = "http://board.tv4user.de/index.php?page=Index";
    private static final String BOARD_URI = "http://board.tv4user.de/index.php?page=Board&boardID=%s";

    private static final String SERIES_LIST_SELECTOR = "select[name=boardID] option:contains(%s)";
    private static final String SEASONS_SELECTOR = "tbody tr td:nth-child(2) div.topic a:contains(%s)";

    @Override
    public void register(String username, String password) {

    }

    @Override
    public List<Series> searchSeries(String series) {
        List<Series> seriesList = new ArrayList<>();
        Document document = HTMLParser.getInstance().getDocument(MAIN_URI);
        if (document != null) {
            Elements elements = document.select(String.format(SERIES_LIST_SELECTOR, series));

            Series s;
            for (Element element : elements) {
                s = new Series(element.text(), element.val());
                seriesList.add(s);
            }
        }

        return seriesList;
    }

    @Override
    public List<Season> searchSeasons(Series series) {
        System.out.println(series);
        List<Season> seasonList = new ArrayList<>();
        Document document = HTMLParser.getInstance().getDocument(String.format(BOARD_URI, series.getLink()));
        Element table = document.getElementsByClass("tablelist").first();

        Elements containers = table.select(String.format(SEASONS_SELECTOR, series.getName()));

        Season s;
        for (Element rows : containers) {
            s = new Season(rows.text(), rows.attr("href"));
            seasonList.add(s);
        }

        return seasonList;
    }

    @Override
    public List<Episode> searchEpisodes(Season season) {
        List<Episode> episodeList = new ArrayList<>();
        Document document = HTMLParser.getInstance().getDocument(season.getLink());
        Elements tables = document.select("table");

        Elements rows;
        Elements head;
        Episode e;
        List<String> resolutionHeader = new ArrayList<>();
        int tableCounter = 0;
        for (Element table : tables) {
            tableCounter++;
            rows = table.select("tbody tr");
            head = rows.first().select("td");
            for (int i = 1; i < head.size(); i++) {
                Element col = head.get(i);
                if (col.text().equalsIgnoreCase("subber")) {
                    break;
                } else {
                    resolutionHeader.add(col.text());
                }
            }

            rows.remove(0);

            for (Element row : rows) {
                e = new Episode();

                if (tableCounter == 1) {
                    e.setLanguage("German");
                } else {
                    e.setLanguage("English");
                }

                e.setEpisodeName(row.select("td.episode").first().text());

                for (int i = 3; i < 3 + resolutionHeader.size(); i++) {
                    Resolution resolution = new Resolution();
                    resolution.setResolution(resolutionHeader.get(i - 3));
                    resolution.setName(row.select("td:nth-child(3) a").text());
                    resolution.setLink(row.select("td:nth-child(3) a").attr("href"));

                    e.getResolutions().add(resolution);
                }

                episodeList.add(e);
            }
        }

        return episodeList;
    }
}
