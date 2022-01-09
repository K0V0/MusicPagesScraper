package com.kovospace.musicpagesscraper.scrapers.bandzone_cz;

import com.kovospace.musicpagesscraper.exceptions.pageException.PageNotFoundException;
import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.interfaces.Track;
import com.kovospace.musicpagesscraper.scrapers.BandsScraperImpl;
import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.interfaces.BandzoneBand;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

// TODO pouzivat vsade Optional(), toto je zrele na Cernobyl
@Service
public  class BandzoneBandsScraper
        extends BandsScraperImpl
{
    private Element section;
    private Element bandsContainer;
    private Elements bandContainers;
    private Element paginator;
    private int currentPageNum;

    public BandzoneBandsScraper() { super(); }

    @Override
    public String requestUrl(String searchedBand, String pageNum) {
        this.currentPageNum = Integer.parseInt(pageNum);
        return String.format("https://bandzone.cz/kapely.html?q=%s&p=%s", searchedBand, pageNum);
    }

    @Override
    public void init() throws PageNotFoundException {
        section = document.getElementById("bandSearch");
        bandsContainer = document.getElementById("searchResults");
        if (bandsContainer == null) { throw new PageNotFoundException(); }
        bandContainers = bandsContainer.getElementsByClass("profileLink"); //NPE
        paginator = document.getElementsByClass("paginator").first();
    }

    @Override
    public int getCurrentPageNum() {
        if (paginator == null) {
            return 1;
        } else {
            return currentPageNum;
        }
    }

    @Override
    public int getCurrentPageItemsCount() {
        return paginationOverlap() ? 0 : bandContainers.size();
    }

    @Override
    public int getPagesCount() {
        if (paginator == null) {
            return 1;
        } else {
            return Integer.parseInt(paginator.attr("data-paginator-pages"));
        }
    }

    @Override
    public int getTotalItemsCount() {
        if (paginator == null) {
            return getCurrentPageItemsCount();
        } else {
            return Integer.parseInt(paginator.attr("data-paginator-items"));
        }
    }

    @Override
    public List<Band> getBands() {
        List<Band> bands = new ArrayList<>();

        if (!paginationOverlap()) {
            for (Element bandContainer : bandContainers) {
                bands.add(new BandzoneBand() {
                    @Override
                    public String getImageUrl() {
                        return bandContainer.getElementsByTag("img").first().attr("src");
                    }
                    @Override
                    public String getGenre() {
                        return bandContainer.getElementsByClass("genre").first().text();
                    }
                    @Override
                    public String getCity() {
                        return bandContainer.getElementsByClass("city").first().text();
                    }
                    @Override
                    public String getPlatform() {
                        return "bandzone";
                    }
                    @Override
                    public List<Track> getTracks() {
                        return null;
                    }
                    @Override
                    public String getTitle() {
                        return bandContainer.getElementsByTag("h4").first().text();
                    }
                    @Override
                    public String getHref() {
                        return "https://bandzone.cz" + bandContainer.getElementsByTag("a").first()
                            .attr("href");
                    }
                    @Override
                    public String getSlug() {
                        return bandContainer.getElementsByTag("a").first().attr("href")
                            .replace("/", "");
                    }
                });
            }
        }
        return bands;
    }

    private boolean paginationOverlap() {
        return currentPageNum > getPagesCount();
        // ^ Bandzone.cz BUGfix
        // or maybe throw exception if page overlap
    }
}
