package com.kovospace.musicpagesscraper.scrapers.bandzone_cz.interfaces;

import com.kovospace.musicpagesscraper.interfaces.Track;
import com.kovospace.musicpagesscraper.interfaces.track.AlbumLabel;
import com.kovospace.musicpagesscraper.interfaces.track.AlbumReleaseYear;
import com.kovospace.musicpagesscraper.interfaces.track.AlbumTitle;

public  interface BandzoneTrack
        extends Track, AlbumTitle, AlbumLabel, AlbumReleaseYear
{

}
