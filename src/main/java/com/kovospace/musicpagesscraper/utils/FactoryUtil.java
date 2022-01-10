package com.kovospace.musicpagesscraper.utils;

import com.kovospace.musicpagesscraper.constants.PlatformConstants;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoPlatformException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

public class FactoryUtil<TYP> {

    /*@Value("${userBucket.path}")
    private String platfomrName;*/

    @Value("#{${platforms}}")
    private Map<String, String> platforms;

    private List<TYP> services;

    @Autowired
    public FactoryUtil(List<TYP> services) {
        this.services = services;
    }

    public TYP build(String platform) throws FactoryException {
        return getPlatformService(platform);
    }

    private TYP getPlatformService(String platform) throws FactoryException
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

    private boolean platformExist(String platformName) {
        System.out.println(platforms);
        return PlatformConstants.PLATFORM_INFOS.containsKey(platformName);
    }

    private boolean classNameMatch(Object object, String platformName, String suffix) {
        String className = PlatformConstants.PLATFORM_INFOS.get(platformName).getClassName() + suffix;
        return object.getClass().getSimpleName().equals(className);
    }

}
