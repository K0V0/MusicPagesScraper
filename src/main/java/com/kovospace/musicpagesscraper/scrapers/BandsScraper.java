package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.exceptions.pageException.PageNotFoundException;
import com.kovospace.musicpagesscraper.interfaces.Bands;

public  interface BandsScraper
        extends Bands
{
    String requestUrl(String searchedBand, String pageNum);
    void init() throws PageNotFoundException;
    Bands fetch(String searchedBand, String pageNum) throws PageNotFoundException;
}
