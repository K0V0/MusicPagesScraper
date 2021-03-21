package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.interfaces.BandsScraperInterface;
import com.kovospace.musicpagesscraper.models.Bands;

public abstract class BandsScraper
                extends MainScraper
                implements BandsScraperInterface {

    public BandsScraper() { super(); }

    public String getBands(String searchedBand, String pageNum) {
        getDocument( requestUrl(searchedBand, pageNum) );
        init();
        if (Integer.parseInt(pageNum) > pagesCount()) {
            return outputJson(null);
        } else {
            return outputJson(new Bands(
                    bands(),
                    pageItemsCount(),
                    pagesCount(),
                    pageNum(),
                    totalItemsCount()
            ));
        }
    }
}
