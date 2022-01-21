package com.kovospace.musicpagesscraper.cahcers.freeteknomusic_org;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.cahcers.BandsCacherImpl;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsCacheRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class FreeTeknoMusicBandsCacher
        extends BandsCacherImpl<FreeTeknoMusicBandsDTO>
{
    @Autowired
    protected FreeTeknoMusicBandsCacher(
            BandsCacheRepository bandsCacheRepository,
            Gson gson,
            ModelMapper modelMapper)
            throws FactoryException
    {
        super(bandsCacheRepository, gson, modelMapper);
    }
}
