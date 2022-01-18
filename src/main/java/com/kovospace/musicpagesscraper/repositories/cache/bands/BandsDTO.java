package com.kovospace.musicpagesscraper.repositories.cache.bands;

import com.kovospace.musicpagesscraper.interfaces.Bands;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public  abstract class BandsDTO
        implements Bands {

    private int currentPageItemsCount;
    private int pagesCount;
    private int currentPageNum;
    private int totalItemsCount;

    @Override
    public int getCurrentPageItemsCount() {
        return currentPageItemsCount;
    }

    @Override
    public int getPagesCount() {
        return pagesCount;
    }

    @Override
    public int getCurrentPageNum() {
        return currentPageNum;
    }

    @Override
    public int getTotalItemsCount() {
        return totalItemsCount;
    }
}
