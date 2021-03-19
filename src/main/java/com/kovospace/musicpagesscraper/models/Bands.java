package com.kovospace.musicpagesscraper.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bands {
    private List<Band> bands;
    private int items_current_page;
    private int pages_count;
    private int current_page;
    private int items_total;

    public Bands() {
        bands = new ArrayList<>();
    }

    public void add(Band band) {
        bands.add(band);
    }

    public List<Band> getBands() {
        return bands;
    }

    public int getItems_current_page() {
        return items_current_page;
    }

    public int getPages_count() {
        return pages_count;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public int getItems_total() {
        return items_total;
    }

    public void setItems_current_page(int items_current_page) {
        this.items_current_page = items_current_page;
    }

    public void setPages_count(int pages_count) {
        this.pages_count = pages_count;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public void setItems_total(int items_total) {
        this.items_total = items_total;
    }
}
