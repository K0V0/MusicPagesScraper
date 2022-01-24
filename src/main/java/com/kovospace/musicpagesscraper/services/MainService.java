package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.base.ServiceSelectorBase;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  abstract class MainService<SCRAPER_SERVICE_IFACE, CACHER_SERVICE_IFACE>
        extends ServiceSelectorBase
{
    private List<SCRAPER_SERVICE_IFACE> scraperServices;
    private List<CACHER_SERVICE_IFACE> cacherServices;
    private Map<String, CACHER_SERVICE_IFACE> cachers;
    private Map<String, SCRAPER_SERVICE_IFACE> scrapers;

    protected MainService(
            List<SCRAPER_SERVICE_IFACE> scraperServices,
            List<CACHER_SERVICE_IFACE> cacherServices
    ) {
        this.scraperServices = scraperServices;
        this.cacherServices = cacherServices;
        this.scrapers = new HashMap<>();
        this.cachers = new HashMap<>();
    }

    @PostConstruct
    private void init() throws FactoryException
    {
        for (String platform : allPlatforms.keySet()) {
            scrapers.put(platform, (SCRAPER_SERVICE_IFACE) getService(platform, scraperServices));
            cachers.put(platform, (CACHER_SERVICE_IFACE) getService(platform, cacherServices));
        }
    }

    protected CACHER_SERVICE_IFACE getCacher(String platform) throws FactoryException {
        containsCheckInList(cachers, platform);
        return cachers.get(platform);
    }

    protected SCRAPER_SERVICE_IFACE getScraper(String platform) throws FactoryException {
        containsCheckInList(scrapers, platform);
        return scrapers.get(platform);
    }
}
