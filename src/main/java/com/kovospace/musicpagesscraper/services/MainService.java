package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoPlatformException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoServiceException;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public  abstract class MainService<SCRAPER_SERVICE_IFACE, CACHER_SERVICE_IFACE>
{
    private List<SCRAPER_SERVICE_IFACE> scraperServices;
    private List<CACHER_SERVICE_IFACE> cacherServices;
    private Map<String, CACHER_SERVICE_IFACE> cachers;
    private Map<String, SCRAPER_SERVICE_IFACE> scrapers;

    @Value("#{${platforms}}")
    protected Map<String, String> allPlatforms;

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

    private Object getService(String platform, List<?> services) throws FactoryException {
        if (!allPlatforms.containsKey(platform)) {
            throw new NoPlatformException();
        }
        return Optional
                .ofNullable(services)
                .flatMap(svcs -> svcs
                        .stream()
                        .filter(svc -> {
                            String serviceType = svc.getClass().getSuperclass().getSimpleName().replace("Impl", "");
                            String className = allPlatforms.get(platform) + serviceType;
                            return svc.getClass().getSimpleName().equals(className);
                        })
                        .findFirst())
                .orElseThrow(NoServiceException::new);
    }

    private void containsCheckInList(Map<String, ?> servicesHolder, String platform) throws FactoryException {
        if (!allPlatforms.containsKey(platform)) {
            throw new NoPlatformException();
        }
        if (!servicesHolder.containsKey(platform)) {
            throw new NoServiceException();
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
