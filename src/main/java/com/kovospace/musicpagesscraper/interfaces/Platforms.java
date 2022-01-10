package com.kovospace.musicpagesscraper.interfaces;

import com.kovospace.musicpagesscraper.repositories.platform.PlatformResponseDTO;

import java.util.List;

public  interface Platforms
        extends PageableItem
{
    List<PlatformResponseDTO> getPlatforms();
}
