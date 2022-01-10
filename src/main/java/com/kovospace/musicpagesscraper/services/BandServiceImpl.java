package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.factories.scrapers.BandScraperFactory;
import com.kovospace.musicpagesscraper.interfaces.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class BandServiceImpl
        implements BandService
{
  private BandScraperFactory bandScraperFactory;

  @Autowired
  public BandServiceImpl(
          BandScraperFactory bandScraperFactory
  ) {
    this.bandScraperFactory = bandScraperFactory;
  }

  @Override
  public Band getBand(String slug, String platform) throws FactoryException {
    return bandScraperFactory.build(platform).fetch(slug);
  }
}
