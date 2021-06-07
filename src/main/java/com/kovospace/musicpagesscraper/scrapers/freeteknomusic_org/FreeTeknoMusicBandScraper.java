package com.kovospace.musicpagesscraper.scrapers.freeteknomusic_org;

import com.kovospace.musicpagesscraper.helpers.MD5helper;
import com.kovospace.musicpagesscraper.helpers.UrlHelper;
import com.kovospace.musicpagesscraper.helpers.VocabularyHelper;
import com.kovospace.musicpagesscraper.interfaces.ScraperItemInterface;
import com.kovospace.musicpagesscraper.interfaces.TrackInterface;
import com.kovospace.musicpagesscraper.scrapers.BandScraperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FreeTeknoMusicBandScraper extends BandScraperImpl {
    private final static String URL = "http://archive.freeteknomusic.org/";
    private static final String MP3_URL = "http://freeteknomusic.org/mp3/" ;
    private static final Pattern albumPattern = Pattern.compile("([^\\/]+)\\/[^\\/]+\\.mp3$");
    private String slug;
    private List<ScraperItemInterface> items;

    @Autowired
    private FreeTeknoMusicScraper scraper;

    public FreeTeknoMusicBandScraper() {
        super();
        items = new ArrayList<>();
    }

    @Override
    protected void getDocument(String url) {
        scraper.setExcludePattern(Pattern.compile("^\\?C\\=\\w\\;"));
        items = scraper.scrape(url)
            .stream()
            .filter(item -> FreeTeknoMusicScraper.mp3filePattern.matcher(item.getHref()).find())
            .collect(Collectors.toList());
    }

    @Override
    public String platformSlug() {
        return "freetekno";
    }

    @Override
    public String requestUrl(String slug) {
        this.slug = slug;
        return URL + slug;
    }

    @Override
    public void init() {}

    @Override
    public String title() {
        return slug;
    }

    @Override
    public String imageUrl() {
        return null;
    }

    @Override
    public String href() {
        return URL + slug;
    }

    @Override
    public String genre() {
        return null;
    }

    @Override
    public String city() {
        return null;
    }

    @Override
    public List<TrackInterface> tracks() {
        return items
            .stream()
            .map(item -> new TrackInterface() {
                @Override
                public String getAlbum() {
                    String album;
                    String part = UrlHelper.decode(item.getHref())
                        .replace(MP3_URL + slug + "/", "");
                    Matcher matcher = albumPattern.matcher(part);
                    if (matcher.find()) {
                        album = matcher.group(1);
                    } else {
                        album = VocabularyHelper.noAlbum;
                    }
                    return album;
                }
                @Override
                public String getPlaysCount() {
                    return null;
                }
                @Override
                public String getHrefHash() {
                    return MD5helper.hash(item.getHref());
                }
                @Override
                public String getDuration() {
                    return null;
                }
                @Override
                public String getTitle() {
                    return item.getTitle();
                }
                @Override
                public String getHref() {
                    return item.getHref();
                }
                @Override
                public String getSlug() {
                    return item.getSlug();
                }
            })
            .collect(Collectors.toList());
    }

}
