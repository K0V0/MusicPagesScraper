package com.kovospace.musicpagesscraper.exceptions.pageException;

import com.kovospace.musicpagesscraper.exceptions.PageException;

public  class PageNotFoundException
        extends PageException
{
  @Override
  public String getMessage() {
    return "page not found";
  }
}
