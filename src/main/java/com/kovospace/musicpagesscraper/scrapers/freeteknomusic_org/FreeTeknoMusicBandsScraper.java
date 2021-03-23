package com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org;

import com.kovospace.musicpagesscraper.models.Band;
import com.kovospace.musicpagesscraper.scrapers.BandsScraper;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FreeTeknoMusicBandsScraper extends BandsScraper {
    private static final int ITEMS_PER_PAGE = 20;
    private static final String URL = "http://archive.freeteknomusic.org/";
    private static final Pattern excludePattern = Pattern.compile("^\\?C\\=\\w\\;|http\\:\\/\\/");
    private String searchedBand;
    private int pageNum;
    private Elements bandsHrefs;
    private List<Band> allBands;
    private List<Band> bands;

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
        bandsHrefs = document
            .getElementById("main")
            .getElementById("text")
            .getElementsByTag("table").first()
            .getElementsByTag("a");
        allBands = bandsHrefs
            .stream()
            .filter(elem -> !excludePattern.matcher(elem.attr("href")).find())
            .map(elem -> new Band(
                elem.text(),
                "",
                URL + elem.attr("href"),
                elem.attr("href"),
                "",
                ""
            ))
            .collect(Collectors.toList());
        if (!this.searchedBand.equals("")) {
            allBands = allBands
                .stream()
                .filter(item -> item.getTitle().contains(this.searchedBand))
                .collect(Collectors.toList());
        }
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
        if (this.pageNum <= pagesCount()) {
            int start = (pageNum - 1) * ITEMS_PER_PAGE;
            int end;
            if (start + ITEMS_PER_PAGE > allBands.size()) {
                end = allBands.size();
            } else {
                end = start + ITEMS_PER_PAGE;
            }
            bands = allBands.subList(start, end);
        } else  {
            bands = new ArrayList<>();
        }
        return bands;
    }
}
