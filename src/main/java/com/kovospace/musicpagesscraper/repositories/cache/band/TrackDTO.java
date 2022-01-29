package com.kovospace.musicpagesscraper.repositories.cache.band;

import com.kovospace.musicpagesscraper.interfaces.Track;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  abstract class TrackDTO
        implements Track
{
    private String title;
    private String href;
    private String slugRef;
    private String hrefHash;
}
