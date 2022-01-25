package com.kovospace.musicpagesscraper.cahcers.bandzone_cz;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.cahcers.BandCacherImpl;
import com.kovospace.musicpagesscraper.repositories.cache.band.BandCacheRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class BandzoneBandCacher
        extends BandCacherImpl<BandzoneBandDTO>
{
    @Autowired
    public BandzoneBandCacher(BandCacheRepository bandCacheRepository, Gson gson, ModelMapper modelMapper) {
        super(bandCacheRepository, gson, modelMapper);
    }
}
