package com.kovospace.musicpagesscraper.cahcers;

import com.kovospace.musicpagesscraper.interfaces.Bands;

public interface BandsCacher {
    Bands fetch(String query, int page);
    void cache(String query, int page, Bands bands);
}
