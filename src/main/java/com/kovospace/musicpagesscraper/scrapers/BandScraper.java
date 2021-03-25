package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.models.BandInterface;
import com.kovospace.musicpagesscraper.interfaces.BandScraperInterface;
import com.kovospace.musicpagesscraper.models.TrackInterface;

import java.util.List;

public abstract class BandScraper
                extends MainScraper
                implements BandScraperInterface {

    public BandScraper() { super(); }

    public String getBand(String slug) {
        getDocument( requestUrl(slug) );
        init();
        return outputJson(new BandInterface() {
            @Override
            public String getTitle() { return title(); }
            @Override
            public String getHref() { return href(); }
            @Override
            public String getSlug() { return slug; }
            @Override
            public String getImageUrl() { return imageUrl(); }
            @Override
            public String getGenre() { return genre(); }
            @Override
            public String getCity() { return city(); }
            @Override
            public String getPlatform() { return platformSlug(); }
            @Override
            public List<TrackInterface> getTracks() { return tracks(); }
        });
    }
}
