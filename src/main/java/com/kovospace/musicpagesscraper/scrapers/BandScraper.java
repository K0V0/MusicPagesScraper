package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.interfaces.TrackInterface;
import java.util.List;

public interface BandScraper {
    String platformSlug();
    String requestUrl(String slug);
    void init();
    String title();
    String imageUrl();
    String href();
    String genre();
    String city();
    List<TrackInterface> tracks();
}
