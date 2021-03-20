package com.kovospace.musicpagesscraper.scrapers.bandzone_cz;

import com.kovospace.musicpagesscraper.models.Band;
import com.kovospace.musicpagesscraper.models.Track;
import com.kovospace.musicpagesscraper.scrapers.BandScraper;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class BandzoneBandScraper extends BandScraper {

    public BandzoneBandScraper() { super(); }

    @Override
    protected Band scrape(String slug) {
        Element tracksList;
        String genreAndCity;
        String title;

        getDocument("https://bandzone.cz/" + slug);

        tracksList = document.getElementById("playlist");
        genreAndCity = document
            .getElementsByClass("profile-name").first()
            .getElementsByClass("cityStyle").first()
            .text();
        title = document.getElementsByClass("profile-name").first()
            .getElementsByClass("cutter").first()
            .text().replace(genreAndCity, "");

        band.setGenre(
            genreAndCity.split("\\/")[0].trim()
        );
        band.setCity(
            genreAndCity.split("\\/")[1].trim()
        );
        band.setTitle(title);
        band.setImageUrl(
            document.getElementById("profilePhoto")
                .getElementsByTag("img").first()
                .attr("src")
        );
        band.setHref(
            "https://bandzone.cz/" + slug
        );
        band.setTracks(
            processTracklist(tracksList)
        );

        return band;
    }

    private List<Track> processTracklist(Element trackslist) {
        List<Track> tracks = new ArrayList<>();
        Pattern urlRegex = Pattern.compile("^(http\\:\\/\\/|https\\:\\/\\/)?(www)?(bandzone\\.cz\\/track\\/)(play)(\\/)(\\d+)(.+)$");
        Pattern durationRegex = Pattern.compile("^PT(\\d*)M?(\\d*\\.*\\d*)S*");
        Pattern albumRegex = Pattern.compile("\\s*\\-*\\s*");
        Pattern playsCountRegex = Pattern.compile("\\D", Pattern.CASE_INSENSITIVE);
        Elements trackContainers = trackslist.getElementsByTag("li");
        Element albumContainer;

        String fullTitle;
        String title;
        String album;
        String playsCount;
        String href;
        String hrefHash;
        String duration;

        for (Element trackContainer : trackContainers) {
            fullTitle = trackContainer.getElementsByClass("title").first().text().trim();
            albumContainer = trackContainer.getElementsByClass("album-title").first();
            if (albumContainer == null) {
                album = "Nezaradené";
                title = fullTitle;
            } else {
                album = albumContainer.text().trim(); // apply album regex
                title = fullTitle.replace(album, "");
            }
            playsCount = trackContainer.getElementsByClass("total-plays").first().text(); // squish and regex
            href = trackContainer.attr("data-source"); // apply regex
            hrefHash = "";
            duration = "";

            tracks.add(new Track(
                fullTitle,
                title,
                album,
                playsCount,
                href,
                hrefHash,
                duration
            ));
        }

        return tracks;
    }
}
