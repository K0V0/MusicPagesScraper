package com.kovospace.musicpagesscraper.scrapers.bandzone_cz;

import com.kovospace.musicpagesscraper.helpers.MD5helper;
import com.kovospace.musicpagesscraper.models.Track;
import com.kovospace.musicpagesscraper.scrapers.BandScraper;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BandzoneBandScraper extends BandScraper {
    private Element tracksList;
    private String genreAndCity;
    private String title;
    private String slug;

    public BandzoneBandScraper() { super(); }

    @Override
    public String requestUrl(String slug) {
        this.slug = slug;
        return "https://bandzone.cz/" + slug;
    }

    @Override
    public void init() {
        tracksList = document
            .getElementById("playlist");
        genreAndCity = document
            .getElementsByClass("profile-name").first()
            .getElementsByClass("cityStyle").first()
            .text();
        title = document
            .getElementsByClass("profile-name").first()
            .getElementsByClass("cutter").first()
            .text().replace(genreAndCity, "");
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String imageUrl() {
        return document
            .getElementById("profilePhoto")
            .getElementsByTag("img").first()
            .attr("src");
    }

    @Override
    public String href() {
        return requestUrl(slug);
    }

    @Override
    public String genre() {
        return genreAndCity.split("\\/")[0].trim();
    }

    @Override
    public String city() {
        return genreAndCity.split("\\/")[1].trim();
    }

    @Override
    public List<Track> tracks() {
        return processTracklist(tracksList);
    }

    private List<Track> processTracklist(Element trackslist) {

        List<Track> tracks = new ArrayList<>();
        Pattern urlRegex = Pattern.compile("^(http\\:\\/\\/|https\\:\\/\\/)?(www)?(bandzone\\.cz\\/track\\/)(play)(\\/)(\\d+)(.+)$");
        String urlReplacement = "$1$2$3download$5$6";
        Pattern durationRegex = Pattern.compile("^PT(\\d*)M?(\\d*\\.*\\d*)S*");
        Pattern albumRegex = Pattern.compile("^\\s*\\-*\\s*");
        Pattern playsCountRegex = Pattern.compile("\\D", Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        Elements trackContainers = trackslist.getElementsByTag("li");
        Element albumContainer;
        Elements trackMetaTags;
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
                album = albumContainer.text().trim();
                title = fullTitle.replace(album, "");
                album = albumRegex.matcher(album).replaceAll("");
            }
            playsCount = playsCountRegex
                .matcher(trackContainer.getElementsByClass("total-plays").first().text())
                .replaceAll("");
            href = urlRegex
                .matcher(trackContainer.attr("data-source"))
                .replaceAll(urlReplacement);
            hrefHash = MD5helper.hash(href);
            trackMetaTags = trackContainer.getElementsByTag("meta");
            duration = "";
            for (Element trackMetaTag : trackMetaTags) {
                if (trackMetaTag.attr("itemprop").equals("duration")) {
                    duration = trackMetaTag.attr("content");
                }
            }
            matcher = durationRegex.matcher(duration);
            if (matcher.find()) {
                duration = String.format(
                        "%s:%s",
                        processNumForClocks(matcher.group(1)),
                        processNumForClocks(matcher.group(2))
                );
            }

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

    private String processNumForClocks(String textNum) {
        String result;
        if (textNum != null && !textNum.equals("")) {
            result = String.valueOf ((int) Double.parseDouble(textNum));
            if (result.length() == 1) { result = "0" + result; }
        } else {
            result = "00";
        }
        return result;
    }
}
