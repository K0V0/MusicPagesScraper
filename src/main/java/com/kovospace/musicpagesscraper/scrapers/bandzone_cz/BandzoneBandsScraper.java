package com.kovospace.musicpagesscraper.scrapers.bandzone_cz;

import com.kovospace.musicpagesscraper.interfaces.BandInterface;
import com.kovospace.musicpagesscraper.scrapers.BandsScraper;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BandzoneBandsScraper extends BandsScraper {
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
    public int pageNum() {
        if (paginator == null) {
            return 1;
        } else {
            return Integer.parseInt(paginator.getElementsByClass("current").first().text());
        }
    }

    @Override
    public int pageItemsCount() {
        return bandContainers.size();
    }

    @Override
    public int pagesCount() {
        if (paginator == null) {
            return 1;
        } else {
            return Integer.parseInt(paginator.attr("data-paginator-pages"));
        }
    }

    @Override
    public int totalItemsCount() {
        if (paginator == null) {
            return pageItemsCount();
        } else {
            return Integer.parseInt(paginator.attr("data-paginator-items"));
        }
    }

    @Override
    public List<BandInterface> bands() {
        List<BandInterface> bands = new ArrayList<>();
        for (Element bandContainer : bandContainers) {
            bands.add(new BandInterface() {
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
