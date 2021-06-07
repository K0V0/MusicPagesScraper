package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.scrapers.BandScraper;
import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.BandzoneBandScraper;
import com.kovospace.musicpagesscraper.utils.PlatformUtil;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public  class BandServiceImpl
        implements BandService
{
  private List<BandScraper> bandScrapers;
  private PlatformUtil platformUtil;

  public BandServiceImpl(
      List<BandScraper> bandScrapers,
      PlatformUtil platformUtil
  ) {
    this.bandScrapers = bandScrapers;
    this.platformUtil = platformUtil;
  }

  @Override
  public String getBand(String slug) {
    bandScrapers.forEach(bandScraper -> {
      if (bandScraper instanceof BandzoneBandScraper) {

      }
    });
    return null;
  }
}
