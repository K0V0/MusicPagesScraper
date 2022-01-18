package com.kovospace.musicpagesscraper.interfaces;

import java.util.List;

public  interface Bands
        extends PageableItem
{
    List<? extends Band> getBands();
}
