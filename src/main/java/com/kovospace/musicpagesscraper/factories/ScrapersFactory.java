package com.kovospace.musicpagesscraper.factories;

import com.kovospace.musicpagesscraper.constants.PlatformConstants;
import com.kovospace.musicpagesscraper.exceptions.ScraperException;
import com.kovospace.musicpagesscraper.exceptions.scraperException.NoPlatformException;
import com.kovospace.musicpagesscraper.exceptions.scraperException.NoScraperException;
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

  protected Object getPlatformScraper(String platform, List<?> scrapers)
  throws ScraperException
  {
    String scraperType = "";
    if (scrapers.isEmpty()) {
      throw new NoScraperException();
    } else {
      scraperType = scrapers.get(0).getClass().getSuperclass().getSimpleName().replace("Impl", "");
    }
    if (platformExist(platform)) {
      for (Object scraper : scrapers) {
        if (classNameMatch(scraper, platform, scraperType)) {
          return scraper;
        }
      }
      throw new NoScraperException();
    }
    throw new NoPlatformException();
  }
}
