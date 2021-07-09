package com.kovospace.musicpagesscraper.factories;

import com.kovospace.musicpagesscraper.exceptions.ScraperException;
import com.kovospace.musicpagesscraper.scrapers.BandScraper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public  class BandScrapersFactory
        extends ScrapersFactory
{
  private List<BandScraper> bandScrapers;

  @Autowired
  public BandScrapersFactory(
    List<BandScraper> bandScrapers
  ) {
    this.bandScrapers = bandScrapers;
  }

  public BandScraper build(String platform)
  throws ScraperException {
    return (BandScraper) getPlatformScraper(platform, bandScrapers);
  }
}
