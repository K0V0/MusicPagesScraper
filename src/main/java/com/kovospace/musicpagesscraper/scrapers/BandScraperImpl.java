package com.kovospace.musicpagesscraper.scrapers;

public abstract class BandScraperImpl
                extends MainScraper
                implements BandScraper
{
    public BandScraperImpl() { super(); }

    public void fetch(String slug) {
        getDocument( requestUrl(slug) );
        init();
    }

    /*public String getBand(String slug) {
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
    }*/
}
