package com.kovospace.musicpagesscraper.models;

import com.kovospace.musicpagesscraper.interfaces.ScraperItemInterface;

public  abstract class ScraperItem
        implements ScraperItemInterface
{
    @Override
    public String toString() {
        return getHref();
    }
}
