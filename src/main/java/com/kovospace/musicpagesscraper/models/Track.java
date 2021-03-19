package com.kovospace.musicpagesscraper.models;

public class Track {
    private String full_title;
    private String title;
    private String album;
    private String plays_count;
    private String href;
    private String href_hash;
    private String duration;

    public Track() {}

    public Track(String full_title, String title, String album, String plays_count, String href, String href_hash, String duration) {
        this.full_title = full_title;
        this.title = title;
        this.album = album;
        this.plays_count = plays_count;
        this.href = href;
        this.href_hash = href_hash;
        this.duration = duration;
    }

    public String getFull_title() {
        return full_title;
    }

    public void setFull_title(String full_title) {
        this.full_title = full_title;
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

    public String getPlays_count() {
        return plays_count;
    }

    public void setPlays_count(String plays_count) {
        this.plays_count = plays_count;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHref_hash() {
        return href_hash;
    }

    public void setHref_hash(String href_hash) {
        this.href_hash = href_hash;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
