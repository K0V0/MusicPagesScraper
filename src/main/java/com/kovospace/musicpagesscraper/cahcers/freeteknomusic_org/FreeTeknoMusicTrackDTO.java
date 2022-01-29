package com.kovospace.musicpagesscraper.cahcers.freeteknomusic_org;

import com.kovospace.musicpagesscraper.repositories.cache.band.TrackDTO;
import com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org.interfaces.FreeTeknoMusicTrack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class FreeTeknoMusicTrackDTO
        extends TrackDTO
        implements FreeTeknoMusicTrack
{
    private String albumTitle;
}
