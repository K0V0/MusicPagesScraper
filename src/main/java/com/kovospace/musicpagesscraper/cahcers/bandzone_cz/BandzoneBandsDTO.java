package com.kovospace.musicpagesscraper.cahcers.bandzone_cz;

import com.kovospace.musicpagesscraper.repositories.cache.bands.BandsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class BandzoneBandsDTO
        extends BandsDTO
{
    private List<BandzoneBandDTO> bands;
}
