package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.utils.FactoryUtil;
import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.scrapers.BandScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class BandServiceImpl
        implements BandService
{
  private FactoryUtil<BandScraper> scrapersFactoryUtil;

  @Autowired
  public BandServiceImpl(
          FactoryUtil<BandScraper> scrapersFactoryUtil
  ) {
    this.scrapersFactoryUtil = scrapersFactoryUtil;
  }

  @Override
  public Band getBand(String slug, String platform) throws FactoryException {
    return scrapersFactoryUtil.build(platform).fetch(slug);
  }
}
