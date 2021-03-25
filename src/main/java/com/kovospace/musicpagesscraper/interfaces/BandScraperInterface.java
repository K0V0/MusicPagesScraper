package com.kovospace.musicpagesscraper.interfaces;

import com.kovospace.musicpagesscraper.models.TrackInterface;
import java.util.List;

public interface BandScraperInterface {
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
