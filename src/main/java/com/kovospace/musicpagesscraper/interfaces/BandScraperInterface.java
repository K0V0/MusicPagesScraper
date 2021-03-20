package com.kovospace.musicpagesscraper.interfaces;

import com.kovospace.musicpagesscraper.models.Track;

import java.util.List;

public interface BandScraperInterface {
    String requestUrl(String slug);
    void init();
    String title();
    String imageUrl();
    String href();
    String genre();
    String city();
    List<Track> tracks();
}
