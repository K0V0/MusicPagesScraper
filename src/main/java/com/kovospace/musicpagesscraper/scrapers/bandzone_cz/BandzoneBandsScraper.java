package com.kovospace.musicpagesscraper.scrapers.bandzone_cz;

import com.kovospace.musicpagesscraper.models.Band;
import com.kovospace.musicpagesscraper.models.Bands;
import com.kovospace.musicpagesscraper.scrapers.BandsScraper;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class BandzoneBandsScraper extends BandsScraper {

    public BandzoneBandsScraper() { super(); }

    @Override
    protected Bands scrape(String searchedBand, String pageNum) {
        Element bandsContainer;
        Elements bandContainers;
        Element paginator;

        getDocument(
            String.format("https://bandzone.cz/kapely.html?q=%s&p=%s", searchedBand, pageNum)
        );

        bandsContainer = document.getElementById("searchResults");
        bandContainers = bandsContainer.getElementsByClass("profileLink");
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

        bands.setItems_current_page(bands.getBands().size());

        paginator = document.getElementsByClass("paginator").first();
        if (paginator == null) {
            bands.setPages_count(1);
            bands.setCurrent_page(1);
            bands.setItems_total(bands.getBands().size());
        } else {
            bands.setCurrent_page(
                    Integer.parseInt(paginator.getElementsByClass("current").first().text())
            );
            bands.setPages_count(
                    Integer.parseInt(paginator.attr("data-paginator-pages"))
            );
            bands.setItems_total(
                    Integer.parseInt(paginator.attr("data-paginator-items"))
            );
        }

        return bands;
    }
}
