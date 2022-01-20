package com.kovospace.musicpagesscraper.cahcers.bandzone_cz;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.cahcers.BandsCacherImpl;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsCacheRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public  class BandzoneBandsCacher
        extends BandsCacherImpl<BandzoneBandsDTO>
{
    @Autowired
    protected BandzoneBandsCacher(
            BandsCacheRepository bandsCacheRepository,
            Gson gson,
            ModelMapper modelMapper)
            throws FactoryException
    {
        super(bandsCacheRepository, gson, modelMapper);
    }
}
