package com.kovospace.musicpagesscraper.scrapers.bandzone_cz;

import com.kovospace.musicpagesscraper.exceptions.PageException;
import com.kovospace.musicpagesscraper.exceptions.pageException.PageNotFoundException;
import com.kovospace.musicpagesscraper.exceptions.pageException.PageScrapingException;
import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.interfaces.Track;
import com.kovospace.musicpagesscraper.scrapers.BandsScraperImpl;
import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.interfaces.BandzoneBand;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public  class BandzoneBandsScraper
        extends BandsScraperImpl
{
    private static final String NO_BANDS_ELEM_TEXT = "Nebyly nalezeny žádné kapely odpovídajicí zadaným kritériím.";
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
    public void init() throws PageException {
        section = Optional.ofNullable(document.getElementById("bandSearch"))
                .orElseThrow(PageScrapingException::new);
        bandsContainer = document.getElementById("searchResults");
        if (bandsContainer == null) {
            boolean noBands = Optional
                    .ofNullable(section.getElementsByClass("help"))
                    .map(elems -> elems
                            .stream()
                            .anyMatch(elem -> elem.text().trim().equals(NO_BANDS_ELEM_TEXT)))
                    .orElseThrow(PageScrapingException::new);
            if (!noBands) {
                throw new PageNotFoundException();
            }
        } else {
            bandContainers = bandsContainer.getElementsByClass("profileLink"); // can be NULL
            paginator = document.getElementsByClass("paginator").first();
        }
    }

    @Override
    public int getCurrentPageNum() {
        if (paginator == null) { return 1; }
        return currentPageNum;
    }

    @Override
    public int getCurrentPageItemsCount() {
        if (bandContainers == null) { return 0; }
        return paginationOverlap() ? 0 : bandContainers.size();
    }

    @Override
    public int getPagesCount() {
        if (paginator == null) { return 1; }
        return Integer.parseInt(paginator.attr("data-paginator-pages"));
    }

    @Override
    public int getTotalItemsCount() {
        if (paginator == null) { return getCurrentPageItemsCount(); }
        return Integer.parseInt(paginator.attr("data-paginator-items"));
    }

    @Override
    public List<Band> getBands() {
        List<Band> bands = new ArrayList<>();
        if (bandContainers != null) {
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
        }
        return bands;
    }

    private boolean paginationOverlap() {
        // Bandzone.cz behavior fix - when querying page that is out of range, random bands are returned.
        return currentPageNum > getPagesCount();
    }
}
