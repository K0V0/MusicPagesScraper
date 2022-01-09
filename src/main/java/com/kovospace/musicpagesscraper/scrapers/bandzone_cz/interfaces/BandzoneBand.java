package com.kovospace.musicpagesscraper.scrapers.bandzone_cz.interfaces;

import com.kovospace.musicpagesscraper.interfaces.Band;

public  interface BandzoneBand
        extends Band
{
    String getImageUrl();
    String getGenre();
    String getCity();
}
