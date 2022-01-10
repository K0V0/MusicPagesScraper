package com.kovospace.musicpagesscraper.factories;

import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoPlatformException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

public abstract class Factory<TYP> {

    private List<TYP> services;

    @Value("#{${platforms}}")
    private Map<String, String> platforms;

    @Autowired
    protected Factory(List<TYP> services) {
        this.services = services;
    }

    public TYP build(String platform) throws FactoryException {
        return getPlatformService(platform);
    }

    protected TYP getPlatformService(String platform) throws FactoryException
    {
        String serviceType = "";
        if (services.isEmpty()) {
            throw new NoServiceException();
        } else {
            serviceType = services.get(0).getClass().getSuperclass().getSimpleName().replace("Impl", "");
        }
        if (platformExist(platform)) {
            for (TYP service : services) {
                if (classNameMatch(service, platform, serviceType)) {
                    return service;
                }
            }
            throw new NoServiceException();
        }
        throw new NoPlatformException();
    }

    protected boolean platformExist(String platformName) {
        return platforms.containsKey(platformName);
    }

    protected boolean classNameMatch(Object object, String platformName, String suffix) {
        String className = platforms.get(platformName) + suffix;
        return object.getClass().getSimpleName().equals(className);
    }

}
