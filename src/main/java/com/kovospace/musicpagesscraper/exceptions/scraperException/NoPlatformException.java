package com.kovospace.musicpagesscraper.exceptions.scraperException;

import com.kovospace.musicpagesscraper.exceptions.ScraperException;

public  class NoPlatformException
        extends ScraperException
{
  @Override
  public String getMessage() {
    return "no given platform exist";
  }
}
