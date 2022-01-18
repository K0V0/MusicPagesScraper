package com.kovospace.musicpagesscraper.factories.cachers;

import com.kovospace.musicpagesscraper.cahcers.BandsCacher;
import com.kovospace.musicpagesscraper.factories.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public  class BandsCacherFactory
        extends Factory<BandsCacher>

{
    @Autowired
    public BandsCacherFactory(List<BandsCacher> services) { super(services); }
}
