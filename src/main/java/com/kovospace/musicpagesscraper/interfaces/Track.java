package com.kovospace.musicpagesscraper.interfaces;

import com.kovospace.musicpagesscraper.interfaces.track.Href;

public  interface Track
        extends ScraperItem, Href
{
    String getHrefHash();
}
