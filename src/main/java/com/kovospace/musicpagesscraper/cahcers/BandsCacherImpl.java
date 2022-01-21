package com.kovospace.musicpagesscraper.cahcers;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.cahcers.bandzone_cz.BandzoneBandsDTO;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoPlatformException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoServiceException;
import com.kovospace.musicpagesscraper.interfaces.Bands;
import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsCacheEntity;
import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsCacheRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

// TODO add caching times for each platform to settings
// TODO preimplementovat na single table inheritance, jedna service ako enkoder/dekoder na json z tabulky podla platformy

public  abstract class BandsCacherImpl<DTOTYP>
        implements BandsCacher
{
    private static final int CACHE_DAYS = 1;
    private BandsCacheRepository bandsCacheRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private String platform;
    private Class<?> dtoClass;

    @Value("#{${platforms}}")
    private Map<String, String> platforms;

    protected BandsCacherImpl (
            BandsCacheRepository bandsCacheRepository,
            Gson gson,
            ModelMapper modelMapper
    ) throws FactoryException {
        this.bandsCacheRepository = bandsCacheRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() throws FactoryException {
        String dtoClassName = ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]
                .toString();
        this.platform = dtoClassName
                .substring(dtoClassName.lastIndexOf('.') + 1)
                .replace("BandsDTO", "")
                .toLowerCase();
        if (!platforms.containsKey(this.platform)) {
            throw new NoPlatformException();
        }
        try {
            dtoClass = Class.forName(dtoClassName.replace("class ", ""));
        } catch (ClassNotFoundException e) {
            throw new NoServiceException();
        }
    }

    @Override
    public Bands fetch(String query, String page) {
            return (Bands) bandsCacheRepository
                    .getFirstByQueryAndPageAndPlatformAndUpdateTimeGreaterThan(query, page, platform,
                            LocalDateTime.now().minus(CACHE_DAYS, ChronoUnit.DAYS))
                    .filter(bandsCacheEntity -> dtoClass != null)
                    .map(bandsCacheEntity -> gson.fromJson(bandsCacheEntity.getJson(), dtoClass))
                    .orElse(null);
    }

    @Override
    public void cache(String query, String page, Bands bands) {
        BandsCacheEntity bandsCacheEntity = new BandsCacheEntity(
                query, page, platform, gson.toJson(modelMapper.map(bands, BandzoneBandsDTO.class)));
        bandsCacheRepository.save(bandsCacheEntity);
        // to overcome need for @Transactional which will break the platform settings from class init
        List<BandsCacheEntity> toDelete = bandsCacheRepository.getAllByQueryAndPageAndPlatformAndUpdateTimeLessThan(
                query, page, platform, LocalDateTime.now().minus(CACHE_DAYS, ChronoUnit.DAYS));
        if (!toDelete.isEmpty()) {
            bandsCacheRepository.deleteAll(toDelete);
        }
    }

}
