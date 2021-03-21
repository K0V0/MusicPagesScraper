package com.kovospace.musicpagesscraper.models;

import com.kovospace.musicpagesscraper.interfaces.TracklistItemInterface;

public class Notice implements TracklistItemInterface {
    private String text;

    public Notice(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
