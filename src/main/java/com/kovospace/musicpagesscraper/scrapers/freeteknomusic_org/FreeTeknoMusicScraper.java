package com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org;

import com.kovospace.musicpagesscraper.helpers.UrlHelper;
import com.kovospace.musicpagesscraper.interfaces.ScraperItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

    public List<ScraperItem> scrape(String url) {
        List<ScraperItem> results = new ArrayList<>();
        Document document;
        try {
            document = Jsoup.connect(url).get();
            results = scrape(document, "", url);
            results.remove(0); // folder up, infinite loop
            List<ScraperItem> results2 = new ArrayList<>();
            for (ScraperItem result : results) {
                if (!mp3filePattern.matcher(result.getHref()).find()) {
                    matcher = directoryPatternFix.matcher(result.getHref());
                    if (matcher.find()) {
                        results2.addAll(
                            scrape(url + "/" + UrlHelper.decode( matcher.group(0)))
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
    public List<ScraperItem> scrape(Document document, String search, String url) {

        List<ScraperItem> result = document
            .getElementById("main")
            .getElementById("text")
            .getElementsByTag("table").first()
            .getElementsByTag("a")
            .stream()
            .filter(elem -> !excludePattern.matcher(elem.attr("href")).find())
            .map(elem -> new ScraperItem() {
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
                    return UrlHelper.decode(elem.attr("href"));
                }
            })
            .collect(Collectors.toList());
        if (!search.equals("")) {
            result = result
                .stream()
                .filter(item -> item.getTitle().contains(search))
                .collect(Collectors.toList());
        }

        return result;
    }

}
