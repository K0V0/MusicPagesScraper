package com.kovospace.musicpagesscraper.repositories.cache.bands;

import com.kovospace.musicpagesscraper.interfaces.Bands;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public  abstract class BandsDTO<PLATFORM_DTO>
        implements Bands {

    private int currentPageItemsCount;
    private int pagesCount;
    private int currentPageNum;
    private int totalItemsCount;
    private List<PLATFORM_DTO> bands;

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

    @Override
    public List<PLATFORM_DTO> getBands() {
        return bands;
    }
}
