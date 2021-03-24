package com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org;

import com.kovospace.musicpagesscraper.helpers.UrlHelper;
import com.kovospace.musicpagesscraper.models.ScraperItem;
import com.kovospace.musicpagesscraper.models.Track;
import com.kovospace.musicpagesscraper.scrapers.BandScraper;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
        items = scraper.scrape(url);
    }

    @Override
    public String requestUrl(String slug) {
        this.slug = slug;
        //return URL + UrlHelper.encode(slug);
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
        // url encode dat
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
        return null;
    }

}
