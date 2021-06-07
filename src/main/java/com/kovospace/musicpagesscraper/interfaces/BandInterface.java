package com.kovospace.musicpagesscraper.interfaces;

import java.util.List;

public interface BandInterface extends ScraperItemInterface {
    String getImageUrl();
    String getGenre();
    String getCity();
    String getPlatform();
    List<TrackInterface> getTracks();
}
