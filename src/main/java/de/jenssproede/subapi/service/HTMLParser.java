package de.jenssproede.subapi.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTMLParser {

    private static final int AMOUNT = 2;
    private static HTMLParser instance;

    private ExecutorService executorService = Executors.newFixedThreadPool(AMOUNT);

    private final String CHARSET = StandardCharsets.UTF_8.name();
    private final String USERAGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36";

    public static HTMLParser getInstance() {
        if (instance == null) {
            instance = new HTMLParser();
        }
        return instance;
    }

    private HTMLParser() {
    }

    public Document getDocument(String uri) {
        return getDocument(uri, new String[0]);
    }

    public Document getDocument(String uri, String...data) {
        System.out.println("Sending a request to " + uri);
        try {
            return Jsoup.connect(uri)
                    .data(data)
                    .userAgent(USERAGENT)
//                    .cookie("", "")
                    .timeout(3000).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
