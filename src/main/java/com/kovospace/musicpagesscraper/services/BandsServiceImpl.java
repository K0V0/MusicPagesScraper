package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.constants.PlatformConstants;
import com.kovospace.musicpagesscraper.dtos.PageableCounterDTO;
import com.kovospace.musicpagesscraper.exceptions.PageException;
import com.kovospace.musicpagesscraper.exceptions.ScraperException;
import com.kovospace.musicpagesscraper.exceptions.pageException.PageNotFoundException;
import com.kovospace.musicpagesscraper.factories.BandsScrapersFactory;
import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.interfaces.Bands;
import com.kovospace.musicpagesscraper.scrapers.BandsScraper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class BandsServiceImpl
        implements BandsService
{
  private BandsScrapersFactory factory;

  @Autowired
  public BandsServiceImpl(
    BandsScrapersFactory factory
  ) {
    this.factory = factory;
  }

  @Override
  public Bands getBands(String query, String page, String platform)
  throws PageException, ScraperException {
    return factory.build(platform).fetch(query, page);
  }

  @Override
  public Bands getBands(String query, String page, Optional<List<String>> platforms) {
    List<Band> bands = new ArrayList<>();
    PageableCounterDTO counter = new PageableCounterDTO();
    counter.setCurrentPageNum(Integer.parseInt(page));
    List<String> scrapers = platforms
      .orElseGet(() -> new ArrayList<>(PlatformConstants.PLATFORM_INFOS.keySet()));

    for (String scraper : scrapers) {
      try {
        BandsScraper bandsScraper = factory.build(scraper);
        bandsScraper.fetch(query, page);
        bands.addAll(bandsScraper.getBands());
        counter.add(bandsScraper);
      } catch (PageException e) {
        // do nothing yet
      } catch (ScraperException e) {
        // do nothing yet
      }
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
