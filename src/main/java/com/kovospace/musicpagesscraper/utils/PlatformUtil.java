package com.kovospace.musicpagesscraper.utils;

import com.kovospace.musicpagesscraper.scrapers.BandScraper;
import com.kovospace.musicpagesscraper.scrapers.BandsScraper;
import java.util.List;

public interface PlatformUtil {

  boolean platformImplemented(String platformName);
  BandsScraper getPlatformBandsScraper(List<BandsScraper> bandsScrapers, String platformName);
  BandScraper getPlatformBandScraper(List<BandScraper> bandScrapers, String platformName);

}
