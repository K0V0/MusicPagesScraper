package com.kovospace.musicpagesscraper.factories.cachers;

import com.kovospace.musicpagesscraper.cahcers.BandsCacher;
import com.kovospace.musicpagesscraper.factories.MainFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public  class BandsCacherFactory
        extends MainFactory<BandsCacher>

{
    @Autowired
    public BandsCacherFactory(List<BandsCacher> services) { super(services); }
}
