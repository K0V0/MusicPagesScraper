package com.kovospace.musicpagesscraper.entities;

import com.kovospace.musicpagesscraper.interfaces.BandInterface;
import com.kovospace.musicpagesscraper.interfaces.Bands;
import java.util.List;

public  class BandsEntity
        implements Bands
{
  private List<BandInterface> bands;
  private int currentPageItemsCount;
  private int pagesCount;
  private int currentPageNum;
  private int totalItemsCount;

  @Override
  public List<BandInterface> getBands() {
    return bands;
  }

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
