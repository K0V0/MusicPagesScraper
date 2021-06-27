package com.kovospace.musicpagesscraper.factories;

import com.kovospace.musicpagesscraper.scrapers.BandsScraper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public  class BandsScrapersFactory
        extends ScrapersFactory
{
  private List<BandsScraper> bandsScrapers;

  @Autowired
  public BandsScrapersFactory(
    List<BandsScraper> bandsScrapers
  ) {
    this.bandsScrapers = bandsScrapers;
  }

  public BandsScraper build(String platform) {
    return (BandsScraper) getPlatformScraper(platform, bandsScrapers);
  }
}

