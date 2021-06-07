package com.kovospace.musicpagesscraper.interfaces;

import java.util.List;

public interface Bands {
    List<BandInterface> getBands();
    int getCurrentPageItemsCount();
    int getPagesCount();
    int getCurrentPageNum();
    int getTotalItemsCount();
}
