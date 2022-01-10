package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.interfaces.Band;

public interface BandService {

  Band getBand(String slug, String platform) throws FactoryException;

}
