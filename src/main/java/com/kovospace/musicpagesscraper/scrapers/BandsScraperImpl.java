package com.kovospace.musicpagesscraper.scrapers;

public abstract class BandsScraperImpl
                extends MainScraper
                implements BandsScraper
{
    public BandsScraper fetch(String searchedBand, String pageNum) {
      getDocument( requestUrl(searchedBand, pageNum) ); // requestUrl() filled in subclasses
      return init() ? this : null;
    }
}
