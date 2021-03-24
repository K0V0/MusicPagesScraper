package com.kovospace.musicpagesscraper.models;

import com.kovospace.musicpagesscraper.interfaces.BandInterface;
import com.kovospace.musicpagesscraper.interfaces.ScraperItemInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bands {
    private List<BandInterface> bands;
    private int currentPageItemsCount;
    private int pagesCount;
    private int currentPageNum;
    private int totalItemsCount;

    public Bands() {
        bands = new ArrayList<>();
    }

    public Bands(List<BandInterface> bands, int currentPageItemsCount, int pagesCount, int currentPageNum, int totalItemsCount) {
        this.bands = bands;
        this.currentPageItemsCount = currentPageItemsCount;
        this.pagesCount = pagesCount;
        this.currentPageNum = currentPageNum;
        this.totalItemsCount = totalItemsCount;
    }

    public List<BandInterface> getBands() {
        return bands;
    }

    public int getCurrentPageItemsCount() {
        return currentPageItemsCount;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public int getTotalItemsCount() {
        return totalItemsCount;
    }

}
