package com.kovospace.musicpagesscraper.cahcers.bandzone_cz;

import com.kovospace.musicpagesscraper.repositories.cache.band.TrackDTO;
import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.interfaces.BandzoneTrack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class BandzoneTrackDTO
        extends TrackDTO
        implements BandzoneTrack

{
    private String albumTitle;
    private String albumReleaseYear;
    private String albumLabel;
}
