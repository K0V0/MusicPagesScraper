package com.kovospace.musicpagesscraper.scrapers.bandzone_cz.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.interfaces.BandzoneJsTracksSourceDTO;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BandzoneTracksExtractorService {
    // TODO move to .properties file settings
    private static final String BANDZONE_DOWNLOAD_SERVER = "https://bandzone.cz/track/download/";
    private static final Pattern pattern = Pattern.compile("const\\s*loadedPlaylist\\s*=\\s*\\[\\{(.*)\\}\\];");
    private static final Type type = new TypeToken<List<BandzoneJsTracksSourceDTO>>(){}.getType();
    private final Gson gson;
    private List<BandzoneJsTracksSourceDTO> trackInfos;

    @Autowired
    public BandzoneTracksExtractorService(Gson gson) {
        this.gson = gson;
    }

    public void extract(Document document) {
        this.trackInfos = (List<BandzoneJsTracksSourceDTO>) Optional
                .ofNullable(document.getElementById("player"))
                .map(element -> element.getElementsByTag("script"))
                .map(Elements::first)
                .map(Element::data)
                .map(data -> {
                    Matcher matcher = pattern.matcher(data);
                    if (matcher.find()) {
                        String text = matcher.group(1);
                        return gson.fromJson(String.format("[{%s}]", text), type);
                    }
                    return null;
                })
                .orElse(new ArrayList<>());
    }

    public BandzoneJsTracksSourceDTO findTrackInfo(String title, String album) {
        return trackInfos
                .stream()
                .filter(i -> i.getTitle().trim().equalsIgnoreCase(title))
                .filter(i -> i.getAlbumTitle().trim().equalsIgnoreCase(album))
                .findFirst()
                .orElse(null);
    }

}
