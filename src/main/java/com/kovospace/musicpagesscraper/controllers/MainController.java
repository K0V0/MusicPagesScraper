package com.kovospace.musicpagesscraper.controllers;

import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.PageException;
import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.interfaces.Bands;
import com.kovospace.musicpagesscraper.interfaces.Platforms;
import com.kovospace.musicpagesscraper.services.BandService;
import com.kovospace.musicpagesscraper.services.BandsService;
import com.kovospace.musicpagesscraper.services.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

  private BandsService bandsService;
  private BandService bandService;
  private PlatformService platformService;

  @Autowired
  public MainController(
    BandsService bandsService,
    BandService bandService,
    PlatformService platformService
  ) {
    this.bandsService = bandsService;
    this.bandService = bandService;
    this.platformService = platformService;
  }

  @GetMapping(value = "/{s}/bands", produces = "application/json;charset=utf-8")
  public Bands getBands(
    @RequestParam(defaultValue = "") String q,
    @RequestParam(defaultValue = "1") int p,
    @PathVariable String s
  )
  throws PageException, FactoryException {
    return bandsService.getBands(q, p, s);
  }

  @GetMapping(value = "/bands", produces = "application/json;charset=utf-8")
  // foo=value1&foo=value2&foo=value3
  public Bands getBands(
    @RequestParam(defaultValue = "") String q,
    @RequestParam(defaultValue = "1") int p,
    @RequestParam(required = false) Optional<List<String>> s // which pages to scrape none = all supported
  ) {
    return bandsService.getBands(q, p, s);
  }

  /*
  q - query - band name or id
  s - platform
  */
  @GetMapping(value = "/{s}/band", produces = "application/json;charset=utf-8")
  public Band getBand(
    @RequestParam String q,
    @PathVariable String s
  ) throws FactoryException {
      return bandService.getBand(q, s);
  }

  @GetMapping(value = "/platforms", produces = "application/json;charset=utf-8")
  public Platforms getPlatforms(
    @RequestParam(defaultValue = "1", required = false) int p
  ) {
    return platformService.getAll();
  }

}
