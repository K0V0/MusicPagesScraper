package com.kovospace.musicpagesscraper.scrapers.bandzone_cz;

import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.interfaces.BandzoneBand;
import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.interfaces.BandzoneJsTracksSourceDTO;
import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.interfaces.BandzoneTrack;
import com.kovospace.musicpagesscraper.interfaces.Track;
import com.kovospace.musicpagesscraper.scrapers.BandScraperImpl;
import com.kovospace.musicpagesscraper.scrapers.bandzone_cz.services.BandzoneTracksExtractorService;
import com.kovospace.musicpagesscraper.utils.MD5Util;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public  class BandzoneBandScraper
        extends BandScraperImpl
        implements BandzoneBand
{
    private static final String BANDZONE_DOWNLOAD_SERVER = "https://bandzone.cz/track/download/";

    private Element tracksListElement;
    private Element profileElement;
    private String genreAndCity;
    private String slug;

    private BandzoneTracksExtractorService tracksExtractor;

    @Autowired
    public BandzoneBandScraper(BandzoneTracksExtractorService tracksExtractor) {
        super();
        this.tracksExtractor = tracksExtractor;
    }

    @Override
    public String getPlatform() {
        return "bandzone";
    }

    @Override
    public String requestUrl(String slug) {
        this.slug = slug;
        return "https://bandzone.cz/" + slug;
    }

    @Override
    public void init() {
        tracksListElement = Optional
                .ofNullable(document.getElementsByClass("player__playlist"))
                .map(Elements::first)
                .orElse(null);
        profileElement = Optional
                .ofNullable(document.getElementById("profilePhoto"))
                //.orElse(null);
                .orElse(new Element("div"));
        genreAndCity = Optional
                .ofNullable(profileElement.getElementsByClass("profile__name"))
                .map(Elements::first)
                .map(element -> element.getElementsByClass("profile__city"))
                .map(Elements::first)
                .map(Element::text)
                .orElse("");
    }

    @Override
    public String getTitle() {
        return Optional.ofNullable(profileElement.getElementsByClass("profile__name"))
                .map(Elements::first)
                .map(element -> element.text().replace(genreAndCity, "").trim())
                .orElse("");
    }

    @Override
    public String getImageUrl() {
        return Optional.ofNullable(profileElement.getElementById("snippet--profilePhoto"))
                .map(element -> element.getElementsByTag("img"))
                .map(Elements::first)
                .map(element -> element.attr("src"))
                .orElse("");
    }

    @Override
    public String getHref() {
        return requestUrl(slug);
    }

    @Override
    public String getSlug() {
        return this.slug;
    }

    @Override
    public String getGenre() {
        return genreAndCity.split("\\/")[0].trim();
    }

    @Override
    public String getCity() {
        return genreAndCity.split("\\/")[1].trim();
    }

    @Override
    public List<Track> getTracks() {
        List<Track> tracks = new ArrayList<>();
        if (tracksListElement == null) {
            // no player/tracklist
            return tracks;
        }
        tracksExtractor.extract(document);

        // TODO add other attributes for track that are avail in scraped javascripts
        Optional.ofNullable(tracksListElement.getElementsByClass("player__playlist__item"))
                .ifPresent(trackElements -> {
                    trackElements.forEach(trackElement -> {

                        String album = Optional
                                .ofNullable(trackElement.getElementsByClass("player__song__album"))
                                .map(Elements::first)
                                .map(element -> element.text().trim())
                                .orElse("");
                        String title = Optional
                                .ofNullable(trackElement.getElementsByClass("player__song__name"))
                                .map(Elements::first)
                                .map(element -> element.text().replace(album, "").trim())
                                .orElse("");
                        BandzoneJsTracksSourceDTO trackInfo = tracksExtractor.findTrackInfo(title, album);
                        if (trackInfo == null) {
                            return;
                        }
                        String href = String.format("%s%s", BANDZONE_DOWNLOAD_SERVER, trackInfo.getTrackId());

                        tracks.add(new BandzoneTrack() {
                            @Override public String getAlbumTitle() { return album; }
                            @Override public String getAlbumReleaseYear() {
                                return Optional.ofNullable(trackInfo.getAlbumReleasedYear()).orElse("");
                            }
                            @Override public String getAlbumLabel() {
                                return Optional.ofNullable(trackInfo.getAlbumLabel()).orElse("");
                            }
                            @Override public String getHrefHash() { return MD5Util.hash(href); }
                            @Override public String getTitle() { return title; }
                            @Override public String getHref() { return href; }
                            // TODO rename to bandId across whole app
                            @Override public String getSlug() { return slug; }
                        });
                    });
                });

        return tracks;
    }

}
