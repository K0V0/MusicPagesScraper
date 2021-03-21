package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.interfaces.BandScraperInterface;
import com.kovospace.musicpagesscraper.models.Band;

public abstract class BandScraper
                extends MainScraper
                implements BandScraperInterface {

    public BandScraper() { super(); }

    public String getBand(String slug) {
        getDocument( requestUrl(slug) );
        init();
        return outputJson(new Band(
            title(),
            imageUrl(),
            href(),
            slug,
            genre(),
            city(),
            tracks()
        ));
    }
}
