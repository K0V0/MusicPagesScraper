package com.kovospace.musicpagesscraper.cahcers.freeteknomusic_org;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.cahcers.BandCacherImpl;
import com.kovospace.musicpagesscraper.repositories.cache.band.BandCacheRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class FreeTeknoMusicBandCacher
        extends BandCacherImpl<FreeTeknoMusicBandDTO>

{
    @Autowired
    public FreeTeknoMusicBandCacher(
            BandCacheRepository bandCacheRepository,
            Gson gson,
            ModelMapper modelMapper)
    {
        super(bandCacheRepository, gson, modelMapper);
    }
}
