package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoPlatformException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoServiceException;
import com.kovospace.musicpagesscraper.factories.MainFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

public  abstract class MainService<SCRAPER_SERVICE_IFACE, CACHER_SERVICE_IFACE>
{
    private MainFactory<SCRAPER_SERVICE_IFACE> scrapersFactory;
    private MainFactory<CACHER_SERVICE_IFACE> cachersFactory;
    private Map<String, CACHER_SERVICE_IFACE> cachers;
    private Map<String, SCRAPER_SERVICE_IFACE> scrapers;

    @Value("#{${platforms}}")
    protected Map<String, String> allPlatforms;

    protected MainService(
            MainFactory<SCRAPER_SERVICE_IFACE> scrapersFactory,
            MainFactory<CACHER_SERVICE_IFACE> cachersFactory
    ) {
        this.scrapersFactory = scrapersFactory;
        this.cachersFactory = cachersFactory;
        this.scrapers = new HashMap<>();
        this.cachers = new HashMap<>();
    }

    @PostConstruct
    protected void init() throws FactoryException
    {
        for (String platform : allPlatforms.keySet()) {
            scrapers.put(platform, scrapersFactory.build(platform));
            cachers.put(platform, cachersFactory.build(platform));
        }
    }

    private void containsCheck(Map<String, ?> servicesHolder, String platform) throws FactoryException {
        if (!allPlatforms.containsKey(platform)) {
            throw new NoPlatformException();
        }
        if (!servicesHolder.containsKey(platform)) {
            throw new NoServiceException();
        }
    }


    protected CACHER_SERVICE_IFACE getCacher(String platform) throws FactoryException {
        containsCheck(cachers, platform);
        return cachers.get(platform);
    }

    protected SCRAPER_SERVICE_IFACE getScraper(String platform) throws FactoryException {
        containsCheck(scrapers, platform);
        return scrapers.get(platform);
    }
}
