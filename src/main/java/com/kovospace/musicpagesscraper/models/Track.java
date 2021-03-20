package com.kovospace.musicpagesscraper.models;

public class Track {
    private String fullTitle;
    private String title;
    private String album;
    private String playsCount;
    private String href;
    private String hrefHash;
    private String duration;

    public Track() {}

    public Track(String fullTitle, String title, String album, String playsCount, String href, String hrefHash, String duration) {
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
