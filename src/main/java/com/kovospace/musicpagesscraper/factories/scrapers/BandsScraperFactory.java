package com.kovospace.musicpagesscraper.factories.scrapers;

import com.kovospace.musicpagesscraper.factories.Factory;
import com.kovospace.musicpagesscraper.scrapers.BandsScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public  class BandsScraperFactory
        extends Factory<BandsScraper>
{
    @Autowired
    public BandsScraperFactory(List<BandsScraper> services) {
        super(services);
    }
}
