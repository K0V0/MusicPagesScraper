package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.interfaces.Bands;

public  interface BandsScraper
        extends Bands
{
    String requestUrl(String searchedBand, String pageNum);
    boolean init();
    BandsScraper fetch(String searchedBand, String pageNum);
}
