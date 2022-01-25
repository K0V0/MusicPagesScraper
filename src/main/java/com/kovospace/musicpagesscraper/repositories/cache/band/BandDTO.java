package com.kovospace.musicpagesscraper.repositories.cache.band;

import com.kovospace.musicpagesscraper.interfaces.Band;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public  abstract class BandDTO<PLATFORM_DTO>
        implements Band
{
    private List<PLATFORM_DTO> tracks;
    private String platform;
    private String title;
    private String href;
    private String slug;
}
