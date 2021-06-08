package com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org;

import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.interfaces.ScraperItemInterface;
import com.kovospace.musicpagesscraper.interfaces.TrackInterface;
import com.kovospace.musicpagesscraper.scrapers.BandsScraperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FreeTeknoMusicBandsScraper extends BandsScraperImpl {
    private static final int ITEMS_PER_PAGE = 20;
    private static final String URL = "http://archive.freeteknomusic.org/";
    private String searchedBand;
    private int pageNum;
    private List<ScraperItemInterface> allBands;
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
    public int getCurrentPageNum() {
        return this.pageNum;
    }

    @Override
    public int getCurrentPageItemsCount() {
        return bands.size();
    }

    @Override
    public int getPagesCount() {
        return (allBands.size() % ITEMS_PER_PAGE) == 0 ? (allBands.size() / ITEMS_PER_PAGE) : ((allBands.size() / ITEMS_PER_PAGE) + 1) ;
    }

    @Override
    public int getTotalItemsCount() {
        return allBands.size();
    }

    @Override
    public List<Band> getBands() {
        if (this.pageNum <= getPagesCount()) {
            int start = (pageNum - 1) * ITEMS_PER_PAGE;
            int end;
            if (start + ITEMS_PER_PAGE > allBands.size()) {
                end = allBands.size();
            } else {
                end = start + ITEMS_PER_PAGE;
            }
            bands = allBands
                .subList(start, end)
                .stream()
                .map(item -> new Band() {
                    @Override
                    public String getImageUrl() {
                        return null;
                    }
                    @Override
                    public String getGenre() {
                        return null;
                    }
                    @Override
                    public String getCity() {
                        return null;
                    }
                    @Override
                    public String getPlatform() { return "freetekno"; }
                    @Override
                    public List<TrackInterface> getTracks() { return null; }
                    @Override
                    public String getTitle() { return item.getTitle(); }
                    @Override
                    public String getHref() { return item.getHref(); }
                    @Override
                    public String getSlug() { return item.getSlug(); }
                })
                .collect(Collectors.toList());
            return bands;
        }
        return new ArrayList<Band>();
    }

}
