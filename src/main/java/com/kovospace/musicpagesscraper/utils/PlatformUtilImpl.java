package com.kovospace.musicpagesscraper.utils;

import com.kovospace.musicpagesscraper.constants.PlatformConstants;
import com.kovospace.musicpagesscraper.scrapers.BandScraper;
import com.kovospace.musicpagesscraper.scrapers.BandsScraper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public  class PlatformUtilImpl
        implements PlatformUtil
{

  @Override
  public boolean platformImplemented(String platformName) {

    return false;
  }

  @Override
  public BandsScraper getPlatformBandsScraper(List<BandsScraper> bandsScrapers, String platformName) {
    for (BandsScraper bandsScraper : bandsScrapers) {
      if (PlatformConstants.PLATFORM_INFOS.containsKey(platformName)) {
        return bandsScraper;
      }
    }
    return null;
  }

  @Override
  public BandScraper getPlatformBandScraper(List<BandScraper> bandScrapers, String platformName) {
    for (BandScraper bandScraper : bandScrapers) {
      if (PlatformConstants.PLATFORM_INFOS.containsKey(platformName)) {
        return bandScraper;
      }
    }
    return null;
  }
}
