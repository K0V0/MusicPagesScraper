package com.kovospace.musicpagesscraper.interfaces;

import java.util.List;

public interface Bands {
    List<Band> getBands();
    int getCurrentPageItemsCount();
    int getPagesCount();
    int getCurrentPageNum();
    int getTotalItemsCount();
}
