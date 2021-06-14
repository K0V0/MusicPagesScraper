package com.kovospace.musicpagesscraper.interfaces;

public interface PageableItem {
  int getCurrentPageItemsCount();
  int getPagesCount();
  int getCurrentPageNum();
  int getTotalItemsCount();
}
