package com.kovospace.musicpagesscraper.utils;

import com.kovospace.musicpagesscraper.constants.PlatformConstants;
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
      if (PlatformConstants.PLATFORM_CLASSES_PREFIXES.containsKey(platformName)) {
        return bandsScraper;
      }
    }
    return null;
  }
}
