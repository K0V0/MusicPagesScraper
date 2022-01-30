package com.kovospace.musicpagesscraper.scrapers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class MainScraper
{
    protected Document document;

    protected void getDocument(String url) {
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
