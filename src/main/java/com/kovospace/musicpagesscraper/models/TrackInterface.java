package com.kovospace.musicpagesscraper.models;

public interface TrackInterface extends ScraperItemInterface {
    String getAlbum();
    String getPlaysCount();
    String getHrefHash();
    String getDuration();
}
