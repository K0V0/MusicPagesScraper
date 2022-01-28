package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.exceptions.PageException;
import com.kovospace.musicpagesscraper.interfaces.Bands;

public abstract class BandsScraperImpl
                extends MainScraper
                implements BandsScraper
{
    public Bands fetch(String searchedBand, String pageNum)
    throws PageException
    {
      getDocument( requestUrl(searchedBand, pageNum) ); // requestUrl() filled in subclasses
      init();
      return this;
    }
}
