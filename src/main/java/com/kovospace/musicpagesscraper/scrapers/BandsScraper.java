package com.kovospace.musicpagesscraper.scrapers;

import com.kovospace.musicpagesscraper.interfaces.BandsScraperInterface;
import com.kovospace.musicpagesscraper.models.Bands;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BandsScraper extends MainScrapper implements BandsScraperInterface {

    @Autowired
    protected Bands bands;

    public BandsScraper() { super(); }

    //protected abstract Bands scrape(String searchedBand, String pageNum);
    //protected abstract String requestUrl(String searchedBand, String pageNum);

    public String getBands(String searchedBand, String pageNum) {
        getDocument( requestUrl(searchedBand, pageNum) );
        init();
        bands.setCurrentPageNum( pageNum() );
        bands.setCurrentPageItemsCount( pageItemsCount() );
        bands.setPagesCount( pagesCount() );
        bands.setTotalItemsCount( totalItemsCount() );
        bands.setBands( bands() );
        return outputJson(
            //scrape(searchedBand, pageNum)
            bands
        );
    }

}
