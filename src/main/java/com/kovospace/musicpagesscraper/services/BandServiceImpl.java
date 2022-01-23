package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.cahcers.BandCacher;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.factories.MainFactory;
import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.scrapers.BandScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class BandServiceImpl
        extends MainService<BandScraper, BandCacher>
        implements BandService
{

  @Autowired
  protected BandServiceImpl(
          MainFactory<BandScraper> scrapersFactory,
          MainFactory<BandCacher> cachersFactory)
  {
    super(scrapersFactory, cachersFactory);
  }

  @Override
  public Band getBand(String slug, String platform) throws FactoryException {
    return getScraper(platform).fetch(slug);
  }
}
