package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.interfaces.Band;

public  interface BandScraper
        extends Band
{
    String requestUrl(String slug);
    void init();
    Band fetch(String slug);
}
