package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.cahcers.BandsCacher;
import com.kovospace.musicpagesscraper.dtos.PageableCounterDTO;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.PageException;
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
        extends MainService<BandsScraper, BandsCacher>
        implements BandsService
{
  @Autowired
  protected BandsServiceImpl(
          List<BandsScraper> scraperServices,
          List<BandsCacher> cahcerServices)
  {
    super(scraperServices, cahcerServices);
  }

  @Override
  public Bands getBands(String query, String page, String platform) throws PageException, FactoryException {
    Bands bands;
    try {
      if ((bands = getCacher(platform).fetch(query, page)) != null) {
        return bands;
      }
    } catch (FactoryException e) {
      // cacher not implemented
      // TODO logging
    }
    bands = getScraper(platform).fetch(query, page);
    try {
      getCacher(platform).cache(query, page, bands);
    } catch (FactoryException e) {
      // cacher not implemented
    }
    //Logger.getAnonymousLogger().log(Level.ALL, "bands");
    return bands;
  }

  @Override
  public Bands getBands(String query, String page, Optional<List<String>> platforms)
  {
    List<Band> bandsList = new ArrayList<>();
    PageableCounterDTO counter = new PageableCounterDTO();
    counter.setCurrentPageNum(Integer.parseInt(page));
    List<String> platformList = platforms
            .orElseGet(() -> new ArrayList<>(allPlatforms.keySet()));

    for (String platform : platformList) {
      try {
        Bands bandsOfPlatform = getBands(query, page, platform);
        bandsList.addAll((List<Band>) bandsOfPlatform.getBands());
        counter.add(bandsOfPlatform);
      } catch (PageException e) {
        // do nothing - just no data from given platform
        continue;
      } catch (FactoryException e) {
        // do nothing - just no data from given platform
        // TODO logging
        continue;
      }
    }

    return new Bands()
    {
      @Override public List<Band> getBands() { return bandsList; }
      @Override public int getCurrentPageItemsCount() { return counter.getCurrentPageItemsCount(); }
      @Override public int getPagesCount() { return counter.getPagesCount(); }
      @Override public int getCurrentPageNum() { return counter.getCurrentPageNum(); }
      @Override public int getTotalItemsCount() { return counter.getTotalItemsCount(); }
    };
  }
}
