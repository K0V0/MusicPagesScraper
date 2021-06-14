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
  public BandsScraper getPlatformBandsScraper(List<BandsScraper> bandsScrapers, String platformName) {
    for (BandsScraper bandsScraper : bandsScrapers) {
      if (platformExist(platformName)) {
        if (classNameMatch(bandsScraper, platformName, "BandsScraper")) {
          return bandsScraper;
        }
      }
    }
    return null; // throw some exceptions instead and catch later
  }

  @Override
  public BandScraper getPlatformBandScraper(List<BandScraper> bandScrapers, String platformName) {
    for (BandScraper bandScraper : bandScrapers) {
      if (platformExist(platformName)) {
        if (classNameMatch(bandScraper, platformName, "BandScraper")) {
          return bandScraper;
        }
      }
    }
    return null; // throw some exceptions instead and catch later
  }

  public boolean platformExist(String platformName) {
    return PlatformConstants.PLATFORM_INFOS.containsKey(platformName);
  }

  public boolean classNameMatch(Object object, String platformName, String suffix) {
    String className = PlatformConstants.PLATFORM_INFOS.get(platformName).getClassName() + suffix;
    return object.getClass().getSimpleName().equals(className);
  }
}
