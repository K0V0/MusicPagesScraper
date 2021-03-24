package com.kovospace.musicpagesscraper.models;

import com.kovospace.musicpagesscraper.interfaces.TracklistItemInterface;

public  class Track
        extends ScraperItem
        implements TracklistItemInterface
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

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPlaysCount() {
        return playsCount;
    }

    public void setPlaysCount(String playsCount) {
        this.playsCount = playsCount;
    }

    public String getHref() {
        return href;
    }

    @Override
    public String getSlug() {
        return "";
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHrefHash() {
        return hrefHash;
    }

    public void setHrefHash(String hrefHash) {
        this.hrefHash = hrefHash;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
