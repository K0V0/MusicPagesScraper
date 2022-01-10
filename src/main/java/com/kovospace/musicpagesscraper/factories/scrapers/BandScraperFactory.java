package com.kovospace.musicpagesscraper.factories.scrapers;

import com.kovospace.musicpagesscraper.factories.Factory;
import com.kovospace.musicpagesscraper.scrapers.BandScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public  class BandScraperFactory
        extends Factory<BandScraper>
{
    @Autowired
    public BandScraperFactory(List<BandScraper> services) {
        super(services);
    }
}
