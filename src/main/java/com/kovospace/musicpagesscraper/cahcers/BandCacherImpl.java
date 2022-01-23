package com.kovospace.musicpagesscraper.cahcers;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.repositories.cache.band.BandCacheRepository;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import javax.annotation.PostConstruct;

public  abstract class BandCacherImpl
        implements BandCacher
{
    private BandCacheRepository bandCacheRepository;
    private Gson gson;
    private ModelMapper modelMapper;

    public BandCacherImpl(
            BandCacheRepository bandCacheRepository,
            Gson gson,
            ModelMapper modelMapper)
    {
        this.bandCacheRepository = bandCacheRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @PostConstruct
    private void init() {

    }

    @Override
    public Band fetch(String query, String platform) {
        return null;
    }


    @Override
    public void cache(String query, String platform, Band band) {

    }
}
