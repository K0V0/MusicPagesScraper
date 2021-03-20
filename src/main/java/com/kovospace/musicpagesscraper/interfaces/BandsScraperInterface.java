package com.kovospace.musicpagesscraper.interfaces;

import com.kovospace.musicpagesscraper.models.Band;

import java.util.List;

public interface BandsScraperInterface {
    String requestUrl(String searchedBand, String pageNum);
    void init();
    int pageNum();
    int pageItemsCount();
    int pagesCount();
    int totalItemsCount();
    List<Band> bands();
}
