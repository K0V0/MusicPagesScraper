package com.kovospace.musicpagesscraper.controllers.bandzone_cz;

import com.kovospace.musicpagesscraper.controllers.MainController;
import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.BandzoneBandScraper;
import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.BandzoneBandsScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bandzone")
public class BandzoneController extends MainController {

    @Autowired
    private BandzoneBandsScraper bandsScraper;

    @Autowired
    private BandzoneBandScraper bandScraper;

    @Override
    public String getBands(String q, String p) {
        return bandsScraper.getBands(q, p);
    }

    @Override
    public String getBand(String q) {
        return bandScraper.getBand(q);
    }
}
