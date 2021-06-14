package com.kovospace.musicpagesscraper.dtos;

import com.kovospace.musicpagesscraper.interfaces.PageableItem;

public  class PageableCounterDTO
        implements PageableItem
{
  private int currentPageItemsCount;
  private int pagesCount;
  private int currentPageNum;
  private int totalItemsCount;

  public PageableCounterDTO() {
    this.currentPageItemsCount = 0;
    this.pagesCount = 1;
    this.currentPageNum = 1;
    this.totalItemsCount = 0;
  }

  @Override
  public int getCurrentPageItemsCount() {
    return this.currentPageItemsCount;
  }

  @Override
  public int getPagesCount() {
    return this.pagesCount;
  }

  @Override
  public int getCurrentPageNum() {
    return this.currentPageNum;
  }

  @Override
  public int getTotalItemsCount() {
    return this.totalItemsCount;
  }

  public void setCurrentPageNum(int currentPageNum) {
    this.currentPageNum = currentPageNum;
  }

  public void add(PageableItem pageableItem) {
    this.currentPageItemsCount += pageableItem.getCurrentPageItemsCount();
    this.pagesCount = Math.max(pageableItem.getPagesCount(), this.pagesCount);
    this.totalItemsCount += pageableItem.getTotalItemsCount();
  }
}
