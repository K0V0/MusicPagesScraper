package com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org;

import com.kovospace.musicpagesscraper.models.ScraperItem;
import com.kovospace.musicpagesscraper.models.Track;
import com.kovospace.musicpagesscraper.scrapers.BandScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FreeTeknoMusicBandScraper extends BandScraper {
    private final static String URL = "http://archive.freeteknomusic.org/";
    private String slug;
    private List<ScraperItem> items;

    @Autowired
    private FreeTeknoMusicScraper scraper;

    // TODO - pozor, poriesit vnorene adresare so skladbami

    public FreeTeknoMusicBandScraper() {
        super();
        items = new ArrayList<>();
    }

    @Override
    protected void getDocument(String url) {
        scraper.setExcludePattern(Pattern.compile("^\\?C\\=\\w\\;"));
        items = scraper.scrape(url)
            .stream()
            .filter(item -> FreeTeknoMusicScraper.mp3filePattern.matcher(item.getHref()).find())
            .collect(Collectors.toList());
        System.out.println(items);
    }

    @Override
    public String requestUrl(String slug) {
        this.slug = slug;
        return URL + slug;
    }

    @Override
    public void init() {}

    @Override
    public String title() {
        return slug;
    }

    @Override
    public String imageUrl() {
        return null;
    }

    @Override
    public String href() {
        return URL + slug;
    }

    @Override
    public String genre() {
        return null;
    }

    @Override
    public String city() {
        return null;
    }

    @Override
    public List<Track> tracks() {
        return items
            .stream()
            .map(Track::new)
            .collect(Collectors.toList());
    }

}
