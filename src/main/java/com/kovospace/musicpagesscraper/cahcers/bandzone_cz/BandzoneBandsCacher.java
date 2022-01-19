package com.kovospace.musicpagesscraper.cahcers.bandzone_cz;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.cahcers.BandsCacherImpl;
import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsCacheRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;

@Service
public  class BandzoneBandsCacher
        extends BandsCacherImpl<BandzoneBandsDTO>
{
    @Autowired
    protected BandzoneBandsCacher(BandsCacheRepository bandsCacheRepository, Gson gson, ModelMapper modelMapper) {
        super(bandsCacheRepository, gson, modelMapper);
    }

    @Override
    protected String getPlatform() {
        //String name = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].toString();
        //System.out.println(name);
        return "bandzone";
    }

}
