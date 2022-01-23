package com.kovospace.musicpagesscraper.cahcers;

import com.kovospace.musicpagesscraper.interfaces.Band;

public interface BandCacher {
    Band fetch(String query, String platform);
    void cache(String query, String platform, Band band);
}
