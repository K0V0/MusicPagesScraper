package com.kovospace.musicpagesscraper.cahcers;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.interfaces.Bands;
import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsCacheEntity;
import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsCacheRepository;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

// TODO add caching times for each platform to settings
// TODO preimplementovat na single table inheritance, jedna service ako enkoder/dekoder na json z tabulky podla platformy

public  abstract class BandsCacherImpl<BANDS_DTO>
        extends MainCacher<BandsCacheRepository, BANDS_DTO>
        implements BandsCacher
{
    protected BandsCacherImpl (
            BandsCacheRepository bandsCacheRepository,
            Gson gson,
            ModelMapper modelMapper
    ) {
        super(bandsCacheRepository, gson, modelMapper);
    }

    @Override
    public Bands fetch(String query, int page) {
            return (Bands) cacherRepository
                    .getFirstByQueryAndPageAndPlatformAndUpdateTimeGreaterThan(query, page, platform,
                            LocalDateTime.now().minus(CACHE_DAYS, ChronoUnit.DAYS))
                    .filter(bandsCacheEntity -> DtoClass != null)
                    .map(bandsCacheEntity -> gson.fromJson(bandsCacheEntity.getJson(), DtoClass))
                    .orElse(null);
    }

    @Override
    public void cache(String query, int page, Bands bands) {
        BandsCacheEntity bandsCacheEntity = new BandsCacheEntity(
                query, page, platform, gson.toJson(modelMapper.map(bands, DtoClass)));
        cacherRepository.save(bandsCacheEntity);
        // to overcome need for @Transactional which will break the platform settings from class init
        List<BandsCacheEntity> toDelete = cacherRepository.getAllByQueryAndPageAndPlatformAndUpdateTimeLessThan(
                query, page, platform, LocalDateTime.now().minus(CACHE_DAYS, ChronoUnit.DAYS));
        if (!toDelete.isEmpty()) {
            cacherRepository.deleteAll(toDelete);
        }
    }

}
