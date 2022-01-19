package com.kovospace.musicpagesscraper.cahcers;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.cahcers.bandzone_cz.BandzoneBandsDTO;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoPlatformException;
import com.kovospace.musicpagesscraper.interfaces.Bands;
import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsCacheEntity;
import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsCacheRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public  abstract class BandsCacherImpl<DTOTYP>
        implements BandsCacher
{
    private BandsCacheRepository bandsCacheRepository;
    private Gson gson;
    private ModelMapper modelMapper;

    @Value("#{${platforms}}")
    private Map<String, String> platforms;

    protected BandsCacherImpl (
            BandsCacheRepository bandsCacheRepository,
            Gson gson,
            ModelMapper modelMapper
    ) {
        this.bandsCacheRepository = bandsCacheRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    protected abstract String getPlatform();

    @Override
    public Bands fetch(String query, String page) throws FactoryException {
        String name = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].toString();

        System.out.println(name);
        Class<?> kls;
        try {
            kls = Class.forName(name.replace("class ", ""));
            System.out.println(Class.forName(name.replace("class ", "")));
            //System.out.println(Class.forName("com.kovospace.musicpagesscraper.cahcers.bandzone_cz.BandzoneBandsDTO"));
            //Class.forName("com.kovospace.musicpagesscraper.cahcers.bandzone_cz.BandzoneBandsDTO");
            //Class.forName("com.kovospace.musicpagesscraper.cahcers.bandzone_cz.BandzoneBandsDTO")
            //System.out.println(Class.forName("BandzoneBandsDTO"));
            return (Bands) bandsCacheRepository.getFirstByQueryAndPageAndPlatform(query, page, getPlatform())
                    .map(bandsCacheEntity -> {
                        //return gson.fromJson(bandsCacheEntity.getJson(), Class.forName(name.replace("class ", "")));
                        return gson.fromJson(bandsCacheEntity.getJson(), kls);
                    })
                    .orElse(null);
        } catch (ClassNotFoundException e) {
        //    throw new NoPlatformException();
        }
        /*return bandsCacheRepository.getFirstByQueryAndPageAndPlatform(query, page, getPlatform())
                .map(bandsCacheEntity -> gson.fromJson(bandsCacheEntity.getJson(), kls))
                .orElse(null);*/
    }

    // TODO time limit na cache
    @Override
    public void cache(String query, String page, Bands bands) {
        BandsCacheEntity bandsCacheEntity = new BandsCacheEntity(
                query, page, getPlatform(), gson.toJson(modelMapper.map(bands, BandzoneBandsDTO.class)));
        bandsCacheRepository.save(bandsCacheEntity);
    }

}
