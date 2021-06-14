package com.kovospace.musicpagesscraper.controllers;

import com.kovospace.musicpagesscraper.interfaces.Band;
import com.kovospace.musicpagesscraper.interfaces.Bands;
import com.kovospace.musicpagesscraper.interfaces.Platforms;
import com.kovospace.musicpagesscraper.services.BandService;
import com.kovospace.musicpagesscraper.services.BandsService;
import com.kovospace.musicpagesscraper.services.PlatformService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/{s}/bands")
  public Bands getBands(
    @RequestParam(defaultValue = "") String q,
    @RequestParam(defaultValue = "1") String p,
    @PathVariable String s
  ) {
    return bandsService.getBands(q, p, s);
  }

  @GetMapping("/bands")
  // foo=value1&foo=value2&foo=value3
  // dat do String[]
  public Bands getBands(
    @RequestParam(defaultValue = "") String q,
    @RequestParam(defaultValue = "1") String p,
    @RequestParam(required = false) Optional<List<String>> s // which pages to scrape none = all supported
  ) {
    return bandsService.getBands(q, p, s);
  }

  @GetMapping("/{s}/band")
  public Band getBand(
    @RequestParam String q,
    @PathVariable String s
  ) {
    return bandService.getBand(q, s);
  }

  @GetMapping("/platforms")
  public Platforms getPlatforms(
    @RequestParam(defaultValue = "1", required = false) String p
  ) {
    return platformService.getAll();
  }

}
