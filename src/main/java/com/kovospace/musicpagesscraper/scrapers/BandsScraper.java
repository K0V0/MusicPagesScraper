package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.interfaces.Bands;

public  interface BandsScraper
        extends Bands
{
    String requestUrl(String searchedBand, String pageNum);
    void init();
    void fetch(String searchedBand, String pageNum);
}
