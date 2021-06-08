package com.kovospace.musicpagesscraper.scrapers.bandzone_cz;

import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.interfaces.TrackInterface;
import com.kovospace.musicpagesscraper.scrapers.BandsScraperImpl;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BandzoneBandsScraper extends BandsScraperImpl {
    private Element bandsContainer;
    private Elements bandContainers;
    private Element paginator;

    public BandzoneBandsScraper() { super(); }

    @Override
    public String requestUrl(String searchedBand, String pageNum) {
        return String.format("https://bandzone.cz/kapely.html?q=%s&p=%s", searchedBand, pageNum);
    }

    @Override
    public void init() {
        bandsContainer = document.getElementById("searchResults");
        bandContainers = bandsContainer.getElementsByClass("profileLink");
        paginator = document.getElementsByClass("paginator").first();
    }

    @Override
    public int getCurrentPageNum() {
        if (paginator == null) {
            return 1;
        } else {
            return Integer.parseInt(paginator.getElementsByClass("current").first().text());
        }
    }

    @Override
    public int getCurrentPageItemsCount() {
        return bandContainers.size();
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
        for (Element bandContainer : bandContainers) {
            bands.add(new Band() {
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
                public String getPlatform() { return "bandzone"; }
                @Override
                public List<TrackInterface> getTracks() { return null; }
                @Override
                public String getTitle() {
                    return bandContainer.getElementsByTag("h4").first().text();
                }
                @Override
                public String getHref() {
                    return "https://bandzone.cz" + bandContainer.getElementsByTag("a").first().attr("href");
                }
                @Override
                public String getSlug() {
                    return bandContainer.getElementsByTag("a").first().attr("href").replace("/", "");
                }
            });
        }
        return bands;
    }

}
