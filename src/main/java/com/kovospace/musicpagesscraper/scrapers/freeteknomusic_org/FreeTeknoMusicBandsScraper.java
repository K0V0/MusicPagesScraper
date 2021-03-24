package com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org;

import com.kovospace.musicpagesscraper.models.Band;
import com.kovospace.musicpagesscraper.models.ScraperItem;
import com.kovospace.musicpagesscraper.scrapers.BandsScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class FreeTeknoMusicBandsScraper extends BandsScraper {
    private static final int ITEMS_PER_PAGE = 20;
    private static final String URL = "http://archive.freeteknomusic.org/";
    private String searchedBand;
    private int pageNum;
    private List<ScraperItem> allBands;
    private List<Band> bands;

    @Autowired
    private FreeTeknoMusicScraper scraper;

    public FreeTeknoMusicBandsScraper() {
        super();
        allBands = new ArrayList<>();
    }

    @Override
    public String requestUrl(String searchedBand, String pageNum) {
        this.searchedBand = searchedBand;
        this.pageNum = Integer.parseInt(pageNum);
        return URL;
    }

    @Override
    public void init() {
        scraper.setExcludePattern(Pattern.compile("^\\?C\\=\\w\\;|http\\:\\/\\/"));
        allBands = scraper.scrape(document, searchedBand, URL);
    }

    @Override
    public int pageNum() {
        return this.pageNum;
    }

    @Override
    public int pageItemsCount() {
        return bands.size();
    }

    @Override
    public int pagesCount() {
        return (allBands.size() % ITEMS_PER_PAGE) == 0 ? (allBands.size() / ITEMS_PER_PAGE) : ((allBands.size() / ITEMS_PER_PAGE) + 1) ;
    }

    @Override
    public int totalItemsCount() {
        return allBands.size();
    }

    @Override
    public List<Band> bands() {
        bands = new ArrayList<>();
        if (this.pageNum <= pagesCount()) {
            int start = (pageNum - 1) * ITEMS_PER_PAGE;
            int end;
            if (start + ITEMS_PER_PAGE > allBands.size()) {
                end = allBands.size();
            } else {
                end = start + ITEMS_PER_PAGE;
            }
            for (ScraperItem item : allBands.subList(start, end)) {
                bands.add(new Band(item));
                //bands.add((Band) item);
                // WHYYY cannot cast
            }
        }
        return bands;
    }
}
