package com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org;

import com.kovospace.musicpagesscraper.models.Track;
import com.kovospace.musicpagesscraper.scrapers.BandScraper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FreeTeknoMusicBandScraper extends BandScraper {

    // TODO - pozor, poriesit vnorene adresare so skladbami

    @Override
    public String requestUrl(String slug) {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public String imageUrl() {
        return null;
    }

    @Override
    public String href() {
        return null;
    }

    @Override
    public String genre() {
        return null;
    }

    @Override
    public String city() {
        return null;
    }

    @Override
    public List<Track> tracks() {
        return null;
    }
}
