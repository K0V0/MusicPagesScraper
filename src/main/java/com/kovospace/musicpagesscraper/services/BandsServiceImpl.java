package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.constants.PlatformConstants;
import com.kovospace.musicpagesscraper.dtos.PageableCounterDTO;
import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.interfaces.Bands;
import com.kovospace.musicpagesscraper.interfaces.PageableItem;
import com.kovospace.musicpagesscraper.scrapers.BandsScraper;
import com.kovospace.musicpagesscraper.utils.PlatformUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
  public Bands getBands(String query, String page, Optional<List<String>> platforms) {
    List<Band> bands = new ArrayList<>();
    PageableCounterDTO counter = new PageableCounterDTO();
    counter.setCurrentPageNum(Integer.parseInt(page));
    List<String> scrapers = platforms
      .orElseGet(() -> new ArrayList<>(PlatformConstants.PLATFORM_INFOS.keySet()));

    for (String scraper : scrapers) {
      BandsScraper bandsScraper = platformUtil.getPlatformBandsScraper(bandsScrapers, scraper);
      bandsScraper.fetch(query, page);
      bands.addAll(bandsScraper.getBands());
      counter.add(bandsScraper);
    }

    return new Bands() {
      @Override public List<Band> getBands() { return bands; }
      @Override public int getCurrentPageItemsCount() { return counter.getCurrentPageItemsCount(); }
      @Override public int getPagesCount() { return counter.getPagesCount(); }
      @Override public int getCurrentPageNum() { return counter.getCurrentPageNum(); }
      @Override public int getTotalItemsCount() { return counter.getTotalItemsCount(); }
    };
  }
}
