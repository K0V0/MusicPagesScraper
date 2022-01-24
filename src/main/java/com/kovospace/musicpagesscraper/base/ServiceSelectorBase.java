package com.kovospace.musicpagesscraper.base;

import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoPlatformException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoServiceException;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class ServiceSelectorBase {

    @Value("#{${platforms}}")
    protected Map<String, String> allPlatforms;

    protected Object getService(String platform, List<?> services) throws FactoryException {
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

    protected void containsCheckInList(Map<String, ?> servicesHolder, String platform) throws FactoryException {
        if (!allPlatforms.containsKey(platform)) {
            throw new NoPlatformException();
        }
        if (!servicesHolder.containsKey(platform)) {
            throw new NoServiceException();
        }
    }

}
