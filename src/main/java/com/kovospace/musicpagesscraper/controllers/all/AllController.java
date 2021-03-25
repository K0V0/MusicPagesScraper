package com.kovospace.musicpagesscraper.controllers.all;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllController {

    @RequestMapping("/bands")
    // foo=value1&foo=value2&foo=value3
    // dat do String[]
    public String getBands(
        @RequestParam(required = false) String[] s, // which pages to scrape none = all supported
        @RequestParam(defaultValue = "") String q,
        @RequestParam(defaultValue = "1") String p
    ) {
        return null;
    }

    @RequestMapping("/band")
    public String getBand() {
        return null;
    }

}
