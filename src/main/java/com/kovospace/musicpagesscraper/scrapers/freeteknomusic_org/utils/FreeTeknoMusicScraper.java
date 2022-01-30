package com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org.utils;

import com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org.interfaces.FreeTeknoMusicItem;
import com.kovospace.musicpagesscraper.utils.UrlUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class FreeTeknoMusicScraper {
    public static final Pattern mp3filePattern = Pattern.compile("\\.mp3$");
    public static final Pattern fullAdressPattern = Pattern.compile("^http://|^https://");
    public static final Pattern directoryPatternFix = Pattern.compile("[^/]+$");
    private Pattern excludePattern;
    private Matcher matcher;

    public FreeTeknoMusicScraper() {
        excludePattern = Pattern.compile("");
    }

    public void setExcludePattern(Pattern pattern) {
        this.excludePattern = pattern;
    }

    public List<FreeTeknoMusicItem> scrape(String url) {
        List<FreeTeknoMusicItem> results = new ArrayList<>();
        Document document;
        try {
            document = Jsoup.connect(url).get();
            results = scrape(document, "", url);
            if (results.size() > 0) {
                results.remove(0); // folder up, otherwise infinite loop
            }
            List<FreeTeknoMusicItem> results2 = new ArrayList<>();
            for (FreeTeknoMusicItem result : results) {
                if (!mp3filePattern.matcher(result.getHref()).find()) {
                    matcher = directoryPatternFix.matcher(result.getHref());
                    if (matcher.find()) {
                        results2.addAll(
                            scrape(url + "/" + UrlUtil.decode( matcher.group(0)))
                        );
                    }
                }
            }
            results.addAll(results2);
        } catch (IOException e) {
            e.getMessage();
        }
        return results;
    }

    // OMG WHYYYYY can I do "new ScraperItem() {...}" which is ABSTRACT class
    public List<FreeTeknoMusicItem> scrape(Document document, String search, String url) {
        List<FreeTeknoMusicItem> result = new ArrayList<>();

        Element element = document
            .getElementById("main")
            .getElementById("text")
            .getElementsByTag("table")
            .first();

        if (element != null) {
            result = element
                .getElementsByTag("a")
                .stream()
                .filter(elem -> !excludePattern.matcher(elem.attr("href")).find())
                .map(elem -> new FreeTeknoMusicItem() {
                    @Override
                    public String getTitle() {
                        return elem.text();
                    }
                    @Override
                    public String getHref() {
                        String elemUrl = elem.attr("href");
                        if (fullAdressPattern.matcher(elemUrl).find()) {
                            return elemUrl;
                        } else {
                            return url + elemUrl;
                        }
                    }
                    @Override
                    public String getSlug() {
                        return UrlUtil.decode(elem.attr("href"));
                    }
                })
                .collect(Collectors.toList());
        }

        if (!search.equals("")) {
            result = result
                .stream()
                .filter(item -> item.getTitle().contains(search))
                .collect(Collectors.toList());
        }

        return result;
    }

}
