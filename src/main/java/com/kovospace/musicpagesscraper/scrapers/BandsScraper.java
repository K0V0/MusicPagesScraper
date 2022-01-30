package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.exceptions.PageException;
import com.kovospace.musicpagesscraper.interfaces.Bands;

public  interface BandsScraper
        extends Bands
{
    String requestUrl(String searchedBand, int pageNum);
    void init() throws PageException;
    Bands fetch(String searchedBand, int pageNum) throws PageException;
}
