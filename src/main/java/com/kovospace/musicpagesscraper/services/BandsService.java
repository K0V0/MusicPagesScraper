package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.interfaces.Bands;
import java.util.List;
import java.util.Optional;

public interface BandsService {
  Bands getBands(String query, String page, String platform);
  Bands getBands(String query, String page, Optional<List<String>> platfroms);
}
