package com.kovospace.musicpagesscraper.interfaces;

public  interface Track
        extends ScraperItem
{
    String getAlbum();
    String getPlaysCount();
    String getHrefHash();
    String getDuration();
}
