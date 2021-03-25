package com.kovospace.musicpagesscraper.models;

import java.util.List;

public interface BandsInterface {
    List<BandInterface> getBands();
    int getCurrentPageItemsCount();
    int getPagesCount();
    int getCurrentPageNum();
    int getTotalItemsCount();
}
