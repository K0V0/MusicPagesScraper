package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.models.Bands;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BandsScraper extends MainScrapper {

    @Autowired
    protected Bands bands;

    public BandsScraper() { super(); }

    protected abstract Bands scrape(String searchedBand, String pageNum);

    public String getBands(String searchedBand, String pageNum) {
        return outputJson(
            scrape(searchedBand, pageNum)
        );
    }

}
