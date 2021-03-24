package com.kovospace.musicpagesscraper.interfaces;

public interface TrackInterface extends ScraperItemInterface {
    String getAlbum();
    String getPlaysCount();
    String getHrefHash();
    String getDuration();
}
