package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.factories.BandScrapersFactory;
import com.kovospace.musicpagesscraper.interfaces.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class BandServiceImpl
        implements BandService
{
  private BandScrapersFactory factory;

  @Autowired
  public BandServiceImpl(
    BandScrapersFactory factory
  ) {
    this.factory = factory;
  }

  @Override
  public Band getBand(String slug, String platform) {
    return factory.build(platform).fetch(slug);
  }
}
