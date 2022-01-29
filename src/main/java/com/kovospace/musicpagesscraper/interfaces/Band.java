package com.kovospace.musicpagesscraper.interfaces;

import java.util.List;

public  interface Band
        extends ScraperItem
{
    String getPlatform();
    String getSlug();
    List<?> getTracks();
}
