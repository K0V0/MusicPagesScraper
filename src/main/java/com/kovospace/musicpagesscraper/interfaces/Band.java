package com.kovospace.musicpagesscraper.interfaces;

import java.util.List;

public  interface Band
        extends ScraperItem
{
    String getImageUrl();
    String getGenre();
    String getCity();
    String getPlatform();
    List<Track> getTracks();
}
