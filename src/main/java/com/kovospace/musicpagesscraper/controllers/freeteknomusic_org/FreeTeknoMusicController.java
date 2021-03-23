package com.kovospace.musicpagesscraper.controllers.freeteknomusic_org;

import com.kovospace.musicpagesscraper.controllers.MainController;
import com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org.FreeTeknoMusicBandScraper;
import com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org.FreeTeknoMusicBandsScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/freeteknomusic")
public class FreeTeknoMusicController extends MainController {

    @Autowired
    private FreeTeknoMusicBandsScraper bandsScraper;

    @Autowired
    private FreeTeknoMusicBandScraper bandScraper;

    @Override
    public String getBands(String q, String p) {
        return bandsScraper.getBands(q, p);
    }

    @Override
    public String getBand(String q) {
        return bandScraper.getBand(q);
    }
}
