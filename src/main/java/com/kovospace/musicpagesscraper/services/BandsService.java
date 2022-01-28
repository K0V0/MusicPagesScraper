package com.kovospace.musicpagesscraper.services;

import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.PageException;
import com.kovospace.musicpagesscraper.interfaces.Bands;
import com.sun.istack.NotNull;

import java.util.List;
import java.util.Optional;

public interface BandsService {

  @NotNull
  Bands getBands(String query, String page, String platform)
      throws PageException, FactoryException;

  @NotNull
  Bands getBands(String query, String page, Optional<List<String>> platfroms);

}
