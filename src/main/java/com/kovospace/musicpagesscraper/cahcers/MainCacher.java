package com.kovospace.musicpagesscraper.cahcers;

import com.google.gson.Gson;
import com.kovospace.musicpagesscraper.base.ServiceSelectorBase;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoPlatformException;
import com.kovospace.musicpagesscraper.exceptions.factoryException.NoServiceException;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;

public  abstract class MainCacher<CACHER_REPOSITORY, RESPONSE_DTO>
        extends ServiceSelectorBase

{
    protected static final int CACHE_DAYS = 1;
    protected CACHER_REPOSITORY cacherRepository;
    protected Gson gson;
    protected ModelMapper modelMapper;
    protected String platform;
    protected Class<RESPONSE_DTO> DtoClass;

    protected MainCacher (
            CACHER_REPOSITORY cacherRepository,
            Gson gson,
            ModelMapper modelMapper
    ) {
        this.cacherRepository = cacherRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() throws FactoryException {
        String dtoClassName = ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]
                .toString();
        this.platform = dtoClassName
                .substring(dtoClassName.lastIndexOf('.') + 1)
                .replaceAll("(BandDTO)*$|(BandsDTO)*$", "")
                .toLowerCase();
        if (!allPlatforms.containsKey(this.platform)) {
            throw new NoPlatformException();
        }
        try {
            DtoClass = (Class<RESPONSE_DTO>) Class.forName(dtoClassName.replace("class ", ""));
        } catch (ClassNotFoundException e) {
            throw new NoServiceException();
        }
    }
}
