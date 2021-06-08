package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.interfaces.Band;

public  interface BandScraper
        extends Band
{
    /*String platformSlug();*/
    String requestUrl(String slug);
    void init();
    void fetch(String slug);
    /*String title();
    String imageUrl();
    String href();
    String genre();
    String city();
    List<TrackInterface> tracks();*/
}
