package com.kovospace.musicpagesscraper.cahcers;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.repositories.cache.band.BandCacheEntity;
import com.kovospace.musicpagesscraper.repositories.cache.band.BandCacheRepository;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public  abstract class BandCacherImpl<BAND_DTO>
        extends MainCacher<BandCacheRepository, BAND_DTO>
        implements BandCacher
{
    public BandCacherImpl(
            BandCacheRepository bandCacheRepository,
            Gson gson,
            ModelMapper modelMapper)
    {
        super(bandCacheRepository, gson, modelMapper);
    }

    @Override
    public Band fetch(String slug) {
        return (Band) cacherRepository.getFirstByPlatformAndSlugAndUpdateTimeGreaterThan(
                platform, slug, LocalDateTime.now().minus(CACHE_DAYS, ChronoUnit.DAYS))
                .map(bandCacheEntity -> gson.fromJson(bandCacheEntity.getJson(), DtoClass))
                .orElse(null);
    }

    @Override
    public void cache(String slug, Band band) {
        BandCacheEntity bandCacheEntity = new BandCacheEntity(
                slug, platform, gson.toJson(modelMapper.map(band, DtoClass)));
        cacherRepository.save(bandCacheEntity);
        // to overcome need for @Transactional which will break the platform settings from class init
        List<BandCacheEntity> toDelete = cacherRepository.getAllByPlatformAndSlugAndUpdateTimeLessThan(
                platform, slug, LocalDateTime.now().minus(CACHE_DAYS, ChronoUnit.DAYS));
        if (!toDelete.isEmpty()) {
            cacherRepository.deleteAll(toDelete);
        }
    }
}
