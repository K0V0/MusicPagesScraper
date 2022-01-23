package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.cahcers.BandsCacher;
import com.kovospace.musicpagesscraper.dtos.PageableCounterDTO;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.PageException;
import com.kovospace.musicpagesscraper.factories.MainFactory;
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
          //MainFactory<BandsScraper> scrapersFactory,
          //MainFactory<BandsCacher> cachersFactory)
          List<BandsScraper> scraperServices,
          List<BandsCacher> cahcerServices)
  {
    super(scraperServices, cahcerServices);
    //super(scrapersFactory, cachersFactory);
  }

  @Override
  public Bands getBands(String query, String page, String platform) throws PageException, FactoryException {
    Bands bands;
    if ((bands = getCacher(platform).fetch(query, page)) != null) {
      return bands;
    }
    bands = getScraper(platform).fetch(query, page);
    getCacher(platform).cache(query, page, bands);
    return bands;
  }

  @Override
  public Bands getBands(String query, String page, Optional<List<String>> platforms)
  {
    List<Band> bands = new ArrayList<>();
    PageableCounterDTO counter = new PageableCounterDTO();
    counter.setCurrentPageNum(Integer.parseInt(page));
    List<String> platformList = platforms
            .orElseGet(() -> new ArrayList<>(allPlatforms.keySet()));

    for (String platform : platformList) {
      try {
        BandsScraper bandsScraper = getScraper(platform);
        bandsScraper.fetch(query, page);
        bands.addAll((List<Band>) bandsScraper.getBands());
        counter.add(bandsScraper);
      } catch (PageException e) {
        // do nothing yet
      } catch (FactoryException e) {
        //
      }
    }

    return new Bands()
    {
      @Override public List<Band> getBands() { return bands; }
      @Override public int getCurrentPageItemsCount() { return counter.getCurrentPageItemsCount(); }
      @Override public int getPagesCount() { return counter.getPagesCount(); }
      @Override public int getCurrentPageNum() { return counter.getCurrentPageNum(); }
      @Override public int getTotalItemsCount() { return counter.getTotalItemsCount(); }
    };
  }
}
