package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.interfaces.Band;

public abstract class BandScraperImpl
                extends MainScraper
                implements BandScraper
{
  public Band fetch(String slug) {
    getDocument( requestUrl(slug) );
    init();
    return this;
  }
}
