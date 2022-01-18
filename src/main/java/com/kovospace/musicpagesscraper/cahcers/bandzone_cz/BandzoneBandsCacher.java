package com.kovospace.musicpagesscraper.cahcers.bandzone_cz;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.cahcers.BandsCacherImpl;
import com.kovospace.musicpagesscraper.interfaces.Bands;
import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsCacheEntity;
import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsCacheRepository;
import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// TODO generifikovat tuto classu
@Service
public  class BandzoneBandsCacher
        extends BandsCacherImpl
{
    private BandsCacheRepository bandsCacheRepository;
    private Gson gson;
    private ModelMapper modelMapper;

    @Autowired
    public BandzoneBandsCacher(
            BandsCacheRepository bandsCacheRepository,
            Gson gson,
            ModelMapper modelMapper
    ) {
        this.bandsCacheRepository = bandsCacheRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    // TODO na platform stringy a podobne veci pouzit enum
    // TODO time limit na cache
    @Override
    public Bands fetch(String query, String page) {
        return bandsCacheRepository.getFirstByQueryAndPageAndPlatform(query, page, "bandzone")
                .map(bandsCacheEntity -> gson.fromJson(bandsCacheEntity.getJson(), BandzoneBandsDTO.class))
                .orElse(null);
    }

    @Override
    public void cache(String query, String page, Bands bands) {
        BandsCacheEntity bandsCacheEntity = new BandsCacheEntity(
                query, page, "bandzone", gson.toJson(modelMapper.map(bands, BandzoneBandsDTO.class)));
        bandsCacheRepository.save(bandsCacheEntity);
    }
}
