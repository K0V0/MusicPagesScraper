package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.interfaces.Bands;
import com.kovospace.musicpagesscraper.scrapers.BandsScraper;
import com.kovospace.musicpagesscraper.utils.PlatformUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class BandsServiceImpl
        implements BandsService
{
  private List<BandsScraper> bandsScrapers;
  private PlatformUtil platformUtil;

  @Autowired
  public BandsServiceImpl(
    List<BandsScraper> bandsScrapers,
    PlatformUtil platformUtil
  ) {
    this.bandsScrapers = bandsScrapers;
    this.platformUtil = platformUtil;
  }

  @Override
  public Bands getBands(String query, String page, String platform) {
    BandsScraper bandsScraper = platformUtil.getPlatformBandsScraper(bandsScrapers, platform);
    bandsScraper.fetch(query, page);
    return bandsScraper;
  }

  @Override
  public String getBands(String query, String page, List<String> platfroms) {
    return null;
  }
}
