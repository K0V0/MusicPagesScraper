package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.interfaces.Bands;
import java.util.List;

public interface BandsService {
  Bands getBands(String query, String page, String platform);
  String getBands(String query, String page, List<String> platfroms);
}
