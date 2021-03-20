package com.kovospace.musicpagesscraper.scrapers.bandzone_cz;

import com.kovospace.musicpagesscraper.models.Band;
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
    public List<Band> bands() {
        List<Band> bands = new ArrayList<>();
        for (Element bandContainer : bandContainers) {
            bands.add(new Band(
                bandContainer.getElementsByTag("h4").first().text(),
                bandContainer.getElementsByTag("img").first().attr("src"),
                "https://bandzone.cz" + bandContainer.getElementsByTag("a").first().attr("href"),
                bandContainer.getElementsByTag("a").first().attr("href").replace("/", ""),
                bandContainer.getElementsByClass("genre").first().text(),
                bandContainer.getElementsByClass("city").first().text()
            ));
        }
        return bands;
    }

}
