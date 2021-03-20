package com.kovospace.musicpagesscraper.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bands {
    private List<Band> bands;
    private int currentPageItemsCount;
    private int pagesCount;
    private int currentPageNum;
    private int totalItemsCount;

    public Bands() {
        bands = new ArrayList<>();
    }

    public Bands(List<Band> bands, int currentPageItemsCount, int pagesCount, int currentPageNum, int totalItemsCount) {
        this.bands = bands;
        this.currentPageItemsCount = currentPageItemsCount;
        this.pagesCount = pagesCount;
        this.currentPageNum = currentPageNum;
        this.totalItemsCount = totalItemsCount;
    }

    public void add(Band band) {
        bands.add(band);
    }

    public List<Band> getBands() {
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

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }

    public void setCurrentPageItemsCount(int currentPageItemsCount) {
        this.currentPageItemsCount = currentPageItemsCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public void setTotalItemsCount(int totalItemsCount) {
        this.totalItemsCount = totalItemsCount;
    }
}
