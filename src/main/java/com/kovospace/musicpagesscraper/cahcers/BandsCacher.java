package com.kovospace.musicpagesscraper.cahcers;

import com.kovospace.musicpagesscraper.interfaces.Bands;

public interface BandsCacher {
    Bands fetch(String query, String page);
    void cache(String query, String page, Bands bands);
}
