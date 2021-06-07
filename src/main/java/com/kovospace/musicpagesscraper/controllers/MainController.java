package com.kovospace.musicpagesscraper.controllers;

import com.kovospace.musicpagesscraper.interfaces.Bands;
import com.kovospace.musicpagesscraper.services.BandService;
import com.kovospace.musicpagesscraper.services.BandsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  private BandsService bandsService;
  private BandService bandService;

  @Autowired
  public MainController(
    BandsService bandsService,
    BandService bandService
  ) {
    this.bandsService = bandsService;
    this.bandService = bandService;
  }

  @GetMapping("/{platform}/bands")
  public Bands getBands(
    @RequestParam(defaultValue = "") String q,
    @RequestParam(defaultValue = "1") String p,
    @PathVariable String platform
  ) {
    return bandsService.getBands(q, p, platform);
  }

  @GetMapping("/bands")
  // foo=value1&foo=value2&foo=value3
  // dat do String[]
  public String getBands(
    @RequestParam(defaultValue = "") String q,
    @RequestParam(defaultValue = "1") String p,
    @RequestParam(required = false) List<String> s // which pages to scrape none = all supported
  ) {
    return bandsService.getBands(q, p, s);
  }

  @GetMapping("/band")
  public String getBand(
    @RequestParam String q
  ) {
    return bandService.getBand(q);
  }

}
