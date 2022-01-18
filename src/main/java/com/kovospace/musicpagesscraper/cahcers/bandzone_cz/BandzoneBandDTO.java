package com.kovospace.musicpagesscraper.cahcers.bandzone_cz;

import com.kovospace.musicpagesscraper.interfaces.Track;
import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.interfaces.BandzoneBand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public  class BandzoneBandDTO
        implements BandzoneBand
{
    private String platform;
    private List<Track> tracks;
    private String title;
    private String href;
    private String imageUrl;
    private String genre;
    private String city;
    private String slug;
}
