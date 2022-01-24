package com.kovospace.musicpagesscraper.cahcers;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.repositories.cache.band.BandCacheRepository;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;

public  abstract class BandCacherImpl<TRACK_DTO>
        extends MainCacher<BandCacheRepository, TRACK_DTO>
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
    public Band fetch(String query, String platform) {
        return null;
    }

    @Override
    public void cache(String query, String platform, Band band) {

    }
}
