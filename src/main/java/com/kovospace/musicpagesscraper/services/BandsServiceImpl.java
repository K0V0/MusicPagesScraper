package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.cahcers.BandsCacher;
import com.kovospace.musicpagesscraper.dtos.PageableCounterDTO;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.PageException;
import com.kovospace.musicpagesscraper.factories.cachers.BandsCacherFactory;
import com.kovospace.musicpagesscraper.factories.scrapers.BandsScraperFactory;
import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.interfaces.Bands;
import com.kovospace.musicpagesscraper.scrapers.BandsScraper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public  class BandsServiceImpl
        implements BandsService
{
  private BandsScraperFactory bandsScraperFactory;
  private BandsCacherFactory bandsCacherFactory;

  @Value("#{${platforms}}")
  private Map<String, String> allPlatforms;

  @Autowired
  public BandsServiceImpl(
          BandsScraperFactory bandsScraperFactory,
          BandsCacherFactory bandsCacherFactory
  ) {
    this.bandsScraperFactory = bandsScraperFactory;
    this.bandsCacherFactory = bandsCacherFactory;
  }

  @Override
  public Bands getBands(String query, String page, String platform)
  throws PageException, FactoryException {
    Bands bands;
    if ((bands = bandsCacherFactory.build(platform).fetch(query, page)) != null) {
      return bands;
    }
    bands = bandsScraperFactory.build(platform).fetch(query, page);
    bandsCacherFactory.build(platform).cache(query, page, bands);
    return bands;
  }

  @Override
  public Bands getBands(String query, String page, Optional<List<String>> platforms) {
    List<Band> bands = new ArrayList<>();
    PageableCounterDTO counter = new PageableCounterDTO();
    counter.setCurrentPageNum(Integer.parseInt(page));
    List<String> scrapers = platforms
      .orElseGet(() -> new ArrayList<>(allPlatforms.keySet()));

    for (String scraper : scrapers) {
      try {
        BandsScraper bandsScraper = bandsScraperFactory.build(scraper);
        bandsScraper.fetch(query, page);
        bands.addAll(bandsScraper.getBands());
        counter.add(bandsScraper);
      } catch (PageException e) {
        // do nothing yet
      } catch (FactoryException e) {
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
