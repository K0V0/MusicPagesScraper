package com.kovospace.musicpagesscraper.factories;

import com.kovospace.musicpagesscraper.constants.PlatformConstants;
import java.util.List;

public abstract class ScrapersFactory
{
  protected boolean platformExist(String platformName) {
    return PlatformConstants.PLATFORM_INFOS.containsKey(platformName);
  }

  protected boolean classNameMatch(Object object, String platformName, String suffix) {
    String className = PlatformConstants.PLATFORM_INFOS.get(platformName).getClassName() + suffix;
    return object.getClass().getSimpleName().equals(className);
  }

  protected Object getPlatformScraper(String platform, List<?> scrapers) {
    String scraperType = "";
    if (scrapers.size() < 1) {
      return null; // throw some exceptions instead and catch later
    } else {
      scraperType = scrapers.get(0).getClass().getSuperclass().getSimpleName().replace("Impl", "");
    }
    for (Object scraper : scrapers) {
      if (platformExist(platform)) {
        if (classNameMatch(scraper, platform, scraperType)) {
          return scraper;
        }
      }
    }
    return null; // throw some exceptions instead and catch later
  }
}
