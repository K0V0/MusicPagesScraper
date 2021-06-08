package com.kovospace.musicpagesscraper.interfaces;

import java.util.List;

public interface Band extends ScraperItemInterface {
    String getImageUrl();
    String getGenre();
    String getCity();
    String getPlatform();
    List<TrackInterface> getTracks();
}
