package com.kovospace.musicpagesscraper.cahcers.bandzone_cz;

import com.kovospace.musicpagesscraper.repositories.cache.band.BandDTO;
import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.interfaces.BandzoneBand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public  class BandzoneBandDTO
        extends BandDTO<BandzoneTrackDTO>
        implements BandzoneBand
{
    private String imageUrl;
    private String genre;
    private String city;
}
