package com.kovospace.musicpagesscraper.models;

import com.kovospace.musicpagesscraper.interfaces.BandInterface;
import com.kovospace.musicpagesscraper.interfaces.TrackInterface;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public  class Band
        extends ScraperItem
        implements BandInterface
{
    private String title;
    private String imageUrl;
    private String href;
    private String slug;
    private String genre;
    private String city;
    private List<TrackInterface> tracks;

    public Band() { super(); }

    public Band(ScraperItem item) {
        this(item.getTitle(), "", item.getHref(), item.getSlug(), "", "");
    }

    public Band(String title, String imageUrl, String href, String slug, String genre, String city) {
        super();
        this.title = title;
        this.imageUrl = imageUrl;
        this.href = href;
        this.slug = slug;
        this.genre = genre;
        this.city = city;
        this.tracks = new ArrayList<>();
    }

    public Band(String title, String imageUrl, String href, String slug, String genre, String city, List<TrackInterface> tracks) {
        this(title, imageUrl, href, slug, genre, city);
        this.tracks = tracks;
    }

    public List<TrackInterface> getTracks() {
        return tracks;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String getHref() {
        return href;
    }

    @Override
    public String getSlug() {
        return slug;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format("{ %s - %s / %s }", title, genre, city);
    }
}
