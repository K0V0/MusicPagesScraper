package com.kovospace.musicpagesscraper.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ControllerInterface {

    @GetMapping("/bands")
    @ResponseBody
    String getBands(
        @RequestParam(defaultValue = "") String q,
        @RequestParam(defaultValue = "1") String p
    );

    @GetMapping("/band")
    @ResponseBody
    String getBand(@RequestParam String q);
}
