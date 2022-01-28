package com.kovospace.musicpagesscraper.exceptions.pageException;

import com.kovospace.musicpagesscraper.exceptions.PageException;

public  class PageScrapingException
        extends PageException
{
    @Override
    public String getMessage() {
        return "page scraping error";
    }
}
