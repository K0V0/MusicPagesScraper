package com.kovospace.musicpagesscraper.cahcers;

import com.kovospace.musicpagesscraper.interfaces.Band;

public interface BandCacher {
    Band fetch(String slug);
    void cache(String slug, Band band);
}
