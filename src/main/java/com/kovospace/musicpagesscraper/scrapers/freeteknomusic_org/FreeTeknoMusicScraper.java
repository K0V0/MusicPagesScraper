package com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org;

import com.kovospace.musicpagesscraper.helpers.UrlHelper;
import com.kovospace.musicpagesscraper.models.ScraperItem;
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
    public static final String URL = "http://archive.freeteknomusic.org";
    private Pattern mp3filePattern = Pattern.compile("\\.mp3$");
    //private Pattern mp3filePattern2 = Pattern.compile("\\.mp3$");
    private Pattern directoryPatternFix = Pattern.compile("[^/]+$");
    private Pattern excludePattern;
    private Matcher matcher;

    public FreeTeknoMusicScraper() {
        excludePattern = Pattern.compile("");
    }

    public void setExcludePattern(Pattern pattern) {
        this.excludePattern = pattern;
    }

    // opravit ConcurrentModificationException

    public List<ScraperItem> scrape(String url) {
        //Pattern mp3filePattern = Pattern.compile("\\.mp3$|\\/$");
        //Pattern directoryPatternFix = Pattern.compile("[^/]+$");
        List<ScraperItem> results = new ArrayList<>();
        //System.out.println(url);
        Document document;
        try {
            document = Jsoup.connect(url).get();
            //System.out.println(document);
            System.out.println(url);
            results = scrape(document, "", url);
            //System.out.println(results);
            results.remove(0);
            for (ScraperItem result : results) {
                //System.out.println(result.getHref());
                if (!mp3filePattern.matcher(result.getHref()).find()) {
                    //results.addAll(scrape((result.getHref())));
                    matcher = directoryPatternFix.matcher(result.getHref());
                    if (matcher.find()) {
                        //System.out.println(matcher.group(0));
                        //System.out.println(url + "/" + matcher.group(0));
                        //System.out.println(url + "/" + UrlHelper.decode( matcher.group(0) ) );
                        String u = url + "/" + UrlHelper.decode( matcher.group(0) );
                        //System.out.println(scrape( url + "/" + matcher.group(0) ));
                        results.addAll(scrape(u));
                    }
                    //System.out.println(result.getHref());

                } else {
                    //results.add()
                }
            }
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
                    return url + elem.attr("href");
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
