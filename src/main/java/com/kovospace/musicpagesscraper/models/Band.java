package com.kovospace.musicpagesscraper.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Band {
    private String title;
    private String imageUrl;
    private String href;
    private String slug;
    private String genre;
    private String city;
    private List<Track> tracks;

    public Band() {}

    public Band(String title, String imageUrl, String href, String slug, String genre, String city) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.href = href;
        this.slug = slug;
        this.genre = genre;
        this.city = city;
        this.tracks = new ArrayList<>();
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHref() {
        return href;
    }

    public String getSlug() {
        return slug;
    }

    public String getGenre() {
        return genre;
    }

    public String getCity() {
        return city;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("{ %s - %s / %s }", title, genre, city);
    }
}
