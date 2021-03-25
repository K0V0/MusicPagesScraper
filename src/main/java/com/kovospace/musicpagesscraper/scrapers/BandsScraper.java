package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.interfaces.BandsScraperInterface;
import com.kovospace.musicpagesscraper.models.BandInterface;
import com.kovospace.musicpagesscraper.models.BandsInterface;
import java.util.List;

public abstract class BandsScraper
                extends MainScraper
                implements BandsScraperInterface {

    public BandsScraper() { super(); }

    public String getBands(String searchedBand, String pageNum) {
        getDocument( requestUrl(searchedBand, pageNum) );
        init();
        if (Integer.parseInt(pageNum) > pagesCount()) {
            return outputJson(null);
        } else {
            return outputJson(new BandsInterface() {
                @Override
                public List<BandInterface> getBands() { return bands(); }
                @Override
                public int getCurrentPageItemsCount() { return pageItemsCount(); }
                @Override
                public int getPagesCount() { return pagesCount(); }
                @Override
                public int getCurrentPageNum() { return pageNum(); }
                @Override
                public int getTotalItemsCount() { return totalItemsCount(); }
            });
        }
    }
}
