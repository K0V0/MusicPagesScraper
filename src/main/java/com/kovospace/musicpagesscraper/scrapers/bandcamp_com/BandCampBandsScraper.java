package com.kovospace.musicpagesscraper.scrapers.bandcamp_com;

import com.kovospace.musicpagesscraper.exceptions.PageException;
import com.kovospace.musicpagesscraper.scrapers.BandsScraperImpl;

import java.util.List;

public  class BandCampBandsScraper
        extends BandsScraperImpl
{
    @Override
    public List<?> getBands() {
        return null;
    }

    @Override
    public int getCurrentPageItemsCount() {
        return 0;
    }

    @Override
    public int getPagesCount() {
        return 0;
    }

    @Override
    public int getCurrentPageNum() {
        return 0;
    }

    @Override
    public int getTotalItemsCount() {
        return 0;
    }

    @Override
    public String requestUrl(String searchedBand, int pageNum) {
        return null;
    }

    @Override
    public void init() throws PageException {

    }
    // search url: https://bandcamp.com/search?q=molchat%2Bdoma
    // https://bandcamp.com/search?item_type=b&page=1&q=band
    // paginator vyzera mat vzdy max 6 stran
    // vyhladavanie ale nevyhladava len kapely ale aj labels
    // TODO spravit utilitu ktora prejde vsetkych 6 stran a vyfiltruje na scrapovanie len html elementy s kapelami
    //  vratit vsetky vysledky asi na jednu stranu
}
