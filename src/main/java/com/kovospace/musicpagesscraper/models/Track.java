package com.kovospace.musicpagesscraper.models;

import com.kovospace.musicpagesscraper.interfaces.TrackInterface;
import com.kovospace.musicpagesscraper.interfaces.TracklistItemInterface;

public  class Track
        extends ScraperItem
        implements TrackInterface, TracklistItemInterface
{
    private String fullTitle;
    private String title;
    private String album;
    private String playsCount;
    private String href;
    private String hrefHash;
    private String duration;
    private String slug;

    public Track() { super(); }

    public Track(ScraperItem item) {
        super();
        this.title = item.getTitle();
        this.href = item.getHref();
        this.slug = item.getSlug();
    }

    public Track(String fullTitle, String title, String album, String playsCount, String href, String hrefHash, String duration) {
        super();
        this.fullTitle = fullTitle;
        this.title = title;
        this.album = album;
        this.playsCount = playsCount;
        this.href = href;
        this.hrefHash = hrefHash;
        this.duration = duration;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAlbum() {
        return album;
    }

    @Override
    public String getPlaysCount() {
        return playsCount;
    }

    @Override
    public String getHref() {
        return href;
    }

    @Override
    public String getSlug() {
        return "";
    }

    @Override
    public String getHrefHash() {
        return hrefHash;
    }

    @Override
    public String getDuration() {
        return duration;
    }

}
