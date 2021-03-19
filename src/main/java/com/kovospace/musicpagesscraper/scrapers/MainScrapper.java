package com.kovospace.musicpagesscraper.scrapers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kovospace.musicpagesscraper.models.Bands;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public abstract class MainScrapper {
    protected Document document;
    protected ObjectMapper mapper;

    public MainScrapper() {
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
        return "error";
    }

}
