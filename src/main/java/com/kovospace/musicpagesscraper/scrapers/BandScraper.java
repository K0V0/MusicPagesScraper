package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.models.Band;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BandScraper extends MainScrapper {

    @Autowired
    protected Band band;

    public BandScraper() { super(); }

    protected abstract Band scrape(String slug);

    public String getBand(String slug) {
        band.setSlug(slug);
        return outputJson(
                scrape(slug)
        );
    }
}
