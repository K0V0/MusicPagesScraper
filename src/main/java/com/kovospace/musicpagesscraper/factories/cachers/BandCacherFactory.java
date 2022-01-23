package com.kovospace.musicpagesscraper.factories.cachers;

import com.kovospace.musicpagesscraper.cahcers.BandCacher;
import com.kovospace.musicpagesscraper.factories.MainFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public  class BandCacherFactory
        extends MainFactory<BandCacher>
{
    @Autowired
    public BandCacherFactory(List<BandCacher> services) { super(services); }
}
