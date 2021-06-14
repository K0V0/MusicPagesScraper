package com.kovospace.musicpagesscraper.scrapers;

public abstract class BandScraperImpl
                extends MainScraper
                implements BandScraper
{
    public BandScraperImpl() { super(); }

    public void fetch(String slug) {
        getDocument( requestUrl(slug) );
        init();
    }
}
