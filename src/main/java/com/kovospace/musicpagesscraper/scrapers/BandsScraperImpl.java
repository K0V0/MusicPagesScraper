package com.kovospace.musicpagesscraper.scrapers;

public abstract class BandsScraperImpl
                extends MainScraper
                implements BandsScraper
{
    public BandsScraperImpl() { super(); }

    public void fetch(String searchedBand, String pageNum) {
        getDocument( requestUrl(searchedBand, pageNum) ); // requestUrl() filled in subclasses
        init(); // in subclasses
    }
}
