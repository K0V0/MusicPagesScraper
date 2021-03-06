package com.kovospace.musicpagesscraper.scrapers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class MainScraper {
    protected Document document;
    protected ObjectMapper mapper;

    public MainScraper() {
        mapper = new ObjectMapper();
    }

    protected void getDocument(String url) {
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    protected String outputJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "no-data-or-error";
    }

}
