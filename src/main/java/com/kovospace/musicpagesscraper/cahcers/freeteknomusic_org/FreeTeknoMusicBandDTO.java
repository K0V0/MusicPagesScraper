package com.kovospace.musicpagesscraper.cahcers.freeteknomusic_org;

import com.kovospace.musicpagesscraper.interfaces.Track;
import com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org.interfaces.FreeTeknoMusicBand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public  class FreeTeknoMusicBandDTO
        implements FreeTeknoMusicBand
{
    private String platform;
    private List<Track> tracks;
    private String title;
    private String href;
    private String slug;
}
