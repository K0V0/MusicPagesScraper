package com.kovospace.musicpagesscraper.exceptions.scraperException;

import com.kovospace.musicpagesscraper.exceptions.ScraperException;

public  class NoScraperException
        extends ScraperException
{
  @Override
  public String getMessage() {
    return "no scraper services in use yet or scraper missing for given platform";
  }
}
